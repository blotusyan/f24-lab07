package AndrewWebServices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class AndrewWebServicesTest {
    Database database;
    RecSys recommender;
    PromoService promoService;
    AndrewWebServices andrewWebService;

    @Before
    public void setUp() {
        // You need to use some mock objects here
        // database = new Database(); // We probably don't want to access our real database...
        database = new InMemoryDatabase();
        // recommender = new RecSys();
        recommender = new InMemoryRecSys();
        // promoService = new PromoService();
        promoService = mock(PromoService.class);

        andrewWebService = new AndrewWebServices(database, recommender, promoService);
    }

    @Test
    public void testLogIn() {
        // This is taking way too long to test
        assertTrue(andrewWebService.logIn("Scotty", 17214));
    }

    @Test
    public void testGetRecommendation() {
        // This is taking way too long to test
        assertEquals("Animal House", andrewWebService.getRecommendation("Scotty"));
    }

    @Test
    public void testSendEmail() {
        // How should we test sendEmail() when it doesn't have a return value?
        // Hint: is there something from Mockito that seems useful here?
        String testEmail = "test@example.com";
        // Call the method under test
        andrewWebService.sendPromoEmail(testEmail);

        // Verify that promoService.mailTo() was called with testEmail
        verify(promoService).mailTo(testEmail);
    }

    @Test
    public void testNoSendEmail() {
        // How should we test that no email has been sent in certain situations (like right after logging in)?
        // Hint: is there something from Mockito that seems useful here?
        andrewWebService.logIn("Scotty", 17214);

        // Verify that promoService.mailTo() was not called
        verifyNoInteractions(promoService);
    }

    @Test
    public void testMultipleEmails() {
        String email1 = "user1@example.com";
        String email2 = "user2@example.com";

        andrewWebService.sendPromoEmail(email1);
        andrewWebService.sendPromoEmail(email2);

        // Verify that mailTo was called twice with the correct emails
        verify(promoService).mailTo(email1);
        verify(promoService).mailTo(email2);
        verify(promoService, times(2)).mailTo(anyString());
    }
    @Test
    public void testSendEmailException() {
        String testEmail = "test@example.com";

        // Configure the mock to throw an exception when mailTo is called
        doThrow(new RuntimeException("Email service down")).when(promoService).mailTo(testEmail);

        try {
            andrewWebService.sendPromoEmail(testEmail);
            // fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            // Assert that the exception message is as expected
            assertEquals("Email service down", e.getMessage());
        }

        // Verify that mailTo was called despite the exception
        verify(promoService).mailTo(testEmail);
    }
}
