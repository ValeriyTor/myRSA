import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {

        String message = "";
        String inputFileName = "openText.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(inputFileName)));
        message = reader.readLine();
        reader.close();
        System.out.println(message);
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter your message:");
//        message = scanner.nextLine();
        // create open and private keys
        myRSA.getKey();
        BigInteger messageCod = new BigInteger(message.getBytes());
        // encrypt message
        BigInteger encryptMessage = myRSA.encrypt(messageCod);
        System.out.println("Encrypt message: " + encryptMessage);
        // encrypt text writes in file
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("encryptText.txt")));
        writer.write(String.valueOf(encryptMessage));
        writer.close();
        BufferedReader newReader = new BufferedReader(new FileReader(new File("encryptText.txt")));
        String encryptMessageFromFile = newReader.readLine();
        newReader.close();
        // check encrypt message
        System.out.println(" Encrypt: " + encryptMessageFromFile);
        BigInteger encryptMessage2 = new BigInteger (encryptMessageFromFile);
        // decrypt message
        BigInteger decryptMessage = myRSA.decrypt(encryptMessage2);
        System.out.println("Decrypt message: " + new String(decryptMessage.toByteArray()));
        BufferedWriter decrypt = new BufferedWriter(new FileWriter(new File("decryptText.txt")));
        decrypt.write(new String(decryptMessage.toByteArray()));
        decrypt.close();
    }
}