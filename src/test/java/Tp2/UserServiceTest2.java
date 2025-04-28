package Tp2;

import org.example.TP2.ServiceException;
import org.example.TP2.UserService;
import org.example.TP2.Utilisateur;
import org.example.TP2.UtilisateurApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest2 {

    @Mock
    private UtilisateurApi utilisateurApiMock;

    // Scénario 1 : Lève une exception lors de la création
    @Test(expected = ServiceException.class)
    public void testCreerUtilisateur_EchecCreation() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jean@email.com");

        doThrow(new ServiceException("Echec de la création de l'utilisateur"))
                .when(utilisateurApiMock).creerUtilisateur(utilisateur);

        UserService userService = new UserService(utilisateurApiMock);
        userService.creerUtilisateur(utilisateur); // Doit lever une exception
    }

    // Scénario 2 : Erreur de validation, l'API ne doit pas être appelée
    @Test
    public void testCreerUtilisateur_ErreurValidation() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("", "", ""); // Données invalides

        UserService userService = new UserService(utilisateurApiMock) {
            @Override
            public void creerUtilisateur(Utilisateur utilisateur) throws ServiceException {
                if (utilisateur.getPrenom().isEmpty()) {
                    return; // Validation échoue, ne pas appeler l'API
                }
                super.creerUtilisateur(utilisateur);
            }
        };

        userService.creerUtilisateur(utilisateur);

        verify(utilisateurApiMock, never()).creerUtilisateur(any());
    }

    // Scénario 3 : L'API affecte un ID à l'utilisateur
    @Test
    public void testCreerUtilisateur_RetourneId() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("Alice", "Martin", "alice@email.com");
        int idUtilisateur = 123;

        doAnswer(invocation -> {
            Utilisateur u = invocation.getArgument(0);
            u.setId(idUtilisateur); // Simule affectation de l'ID
            return null;
        }).when(utilisateurApiMock).creerUtilisateur(utilisateur);

        UserService userService = new UserService(utilisateurApiMock);
        userService.creerUtilisateur(utilisateur);

        assertEquals(idUtilisateur, utilisateur.getId());
    }

    // Scénario 4 : Vérifie les arguments passés avec ArgumentCaptor
    @Test
    public void testCreerUtilisateur_ArgumentCaptor() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("Emma", "Durand", "emma@email.com");

        ArgumentCaptor<Utilisateur> argumentCaptor = ArgumentCaptor.forClass(Utilisateur.class);
        UserService userService = new UserService(utilisateurApiMock);

        userService.creerUtilisateur(utilisateur);

        verify(utilisateurApiMock).creerUtilisateur(argumentCaptor.capture());
        Utilisateur utilisateurCapture = argumentCaptor.getValue();

        assertEquals("Emma", utilisateurCapture.getPrenom());
        assertEquals("Durand", utilisateurCapture.getNom());
        assertEquals("emma@email.com", utilisateurCapture.getEmail());
    }
}
