package myResources;

//import com.paystack.lib.*;
//import com.paystack.lib.model.Transaction;
//import com.paystack.lib.model.TransactionInitializeResponse;

public class PaystackIntegration {

    public static void main(String[] args) {
        String apiKey = "sk_test_add30223adcc787d846745fa825a4891436f638d"; //YOUR_API_KEY
//        Paystack paystack = new Paystack(apiKey);

        // Create a new transaction
//        TransactionInitializeResponse transactionResponse = paystack.transaction().initialize(
//                500000,               // Amount in kobo (e.g., 5000 represents ₦50.00)
//                "test@example.com",  // Customer's email address
//                "NGN"                // Currency code (e.g., NGN for Nigerian Naira)
//        );

        // Get the authorization URL for payment
//        String authorizationUrl = transactionResponse.getAuthorizationUrl();

        // Redirect the user to the authorization URL for payment

        // After successful payment, you will receive a callback with a reference
        String reference = "TRANSACTION_REFERENCE_FROM_CALLBACK";

        // Verify the transaction
//        Transaction transaction = paystack.transaction().verify(reference);

        // Get transaction details
//        String status = transaction.getStatus();
//        String customerEmail = transaction.getCustomer().getEmail();
//        int amount = transaction.getAmount();

        // Perform necessary actions based on the transaction details
//        System.out.println("Transaction Status: " + status);
//        System.out.println("Customer Email: " + customerEmail);
//        System.out.println("Transaction Amount: " + amount / 100.0); // Convert amount back to naira

        // Handle any exceptions that may occur during the process
    }
}