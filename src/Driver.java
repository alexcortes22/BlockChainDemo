import java.security.*;
/**
 * Driver uses the MedicalBlock, MedicalBlockChain and Medical Data classes and functions
 * within those classes.
 * @author Alex Cortes
 *
 */
public class Driver {
	public static void main(String[] args){
        //creating the Medical Block Chain
        MedicalBlockChain blockChain = new MedicalBlockChain();

        //creating Data for Patients
        MedicalData secondPatient = new MedicalData("Bon", "Sy", 1, "Great Data Mining Professor");
        MedicalData thirdPatient = new MedicalData("Ayman", "Zeidan", 2, "Technical Lead");
        MedicalData fourthPatient = new MedicalData("Jin", "Chen", 3, "Android Developer");

        //Creating Blocks from the patients and adding them to the Medical Block Chain
        System.out.println("Mining block 1......");
        blockChain.addBlock(new MedicalBlock(1, "02/28/2019", secondPatient, ""));
        System.out.println("Mining block 2......");
        blockChain.addBlock(new MedicalBlock(2, "02/28/2019", thirdPatient, ""));
        System.out.println("Mining block 3......");
        blockChain.addBlock(new MedicalBlock(3, "02/28/2019", fourthPatient, ""));
        
        //printing out the blockchain
        blockChain.printChain();
        
        //checking the validity of the blockchain
        System.out.println("Is the chain valid?: " + blockChain.isChainValid());
        
        //tampering with the block chain by changing data.
        blockChain.chain.get(1).data.first = "Not Bon";
        
        //checking again if the block chain is valid. Expected result is false. Fails because hash
        //is not equal to expected hash calculation. 
        System.out.println("Is the chain valid? Should be no: " + blockChain.isChainValid());
        
        //trying to be clever by changing the hash value to be the new calculated value for the new data
        try {
        	blockChain.chain.get(1).hash = blockChain.chain.get(1).calculateHash();
        }catch(NoSuchAlgorithmException nsae) {
        	System.out.println("Cant change hash cause algorithm doesnt exist!");
        }
        //wont work because relationship with previous block is broken
        System.out.println("Is the chain valid? Should be no: " + blockChain.isChainValid());
    }

}
