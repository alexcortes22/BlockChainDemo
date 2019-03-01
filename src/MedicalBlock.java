
import java.nio.charset.StandardCharsets;
import java.security.*;
import javax.crypto.*;

/**
 * @Title: This class describes the properties and constructor for a Block in this Medical Block Chain.
 * @author: Alex Cortes
 * @Date: 02/28/2018
 *
 */
public class MedicalBlock {
    int index;
    String timestamp;
    MedicalData data;
    String previousHash;
    String hash;
    int nonce = 0;;

    /**
     * Constructor that creates a Block in the Medical Block chain
     * @param index
     * @param timestamp
     * @param data
     * @param previousHash
     */
    public MedicalBlock(int index, String timestamp, MedicalData data, String previousHash){
        this.index = index;
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = previousHash;
        try {
            this.hash = this.calculateHash();
        }catch(NoSuchAlgorithmException nsae){
            System.out.println("No Such Algorithm Exists!");
        }
    }

    /**
     * CalculateHash uses MessageDigest to create a hash of the information of the patient.
     * @return A String that represents the Hash calculated using the SHA256 algorithm
     * @throws NoSuchAlgorithmException
     */
    public String calculateHash() throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        String block = Integer.toString(this.index) + previousHash +
                        timestamp + data.getInfo() + Integer.toString(this.nonce);
        byte[] inputData = block.getBytes(StandardCharsets.UTF_8);
        messageDigest.update(inputData);
        byte[] digest = messageDigest.digest();
        StringBuffer sb = new StringBuffer();
        for(Byte b: digest){
            sb.append(Integer.toHexString(b & 0xff).toString());
        }
        return sb.toString();
    }

    /**
     * This method demonstrates the Proof of Work aspect of a block chain. In terms of the bitcoin block chain
     * this is the mining portion. We are influencing the hash function
     * @param difficulty
     */
    public void proofOfWork(int difficulty) {
    	char[] prefix = new char[difficulty];
    	for(int i = 0; i < prefix.length; i++) {
    		prefix[i] = '0';
    	}
    	String prefixString = new String(prefix);
    	while(!this.hash.substring(0, difficulty).equals(prefixString)) {
    		this.nonce++;
    		try {
    			this.hash = this.calculateHash();
    		}catch(NoSuchAlgorithmException nsae) {
    			System.out.println("Can not create hash because algorithm does not exist!");
    		}
    	}
    }
    public String printBlock(){
        return "Index: " + this.index + "\n" +
                            "Timestamp: " + this.timestamp + "\n" +
                            "Patient Data: " + this.data.getInfo()+ "\n" +
                            "Previous Hash: " + this.previousHash;
    }
}
