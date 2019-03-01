
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MedicalBlockChain {
    ArrayList<MedicalBlock> chain;
    int DIFFICULTY = 3;
    public MedicalBlockChain(){
        this.chain = new ArrayList<MedicalBlock>();
        this.chain.add(this.createGenesisBlock());
    }

    public MedicalBlock createGenesisBlock(){
        MedicalData patient = new MedicalData("Alex", "Cortes", 0, "No Notes");
        return new MedicalBlock(0,"02/28/2019", patient, "0");
    }

    public MedicalBlock getLatestBlock(){
        MedicalBlock lastBlock = this.chain.get(this.chain.size() - 1);
        return lastBlock;
    }

    public void addBlock(MedicalBlock newBlock){
        newBlock.previousHash = this.getLatestBlock().hash;
        
        newBlock.proofOfWork(this.DIFFICULTY);
       
        this.chain.add(newBlock);
    }

    public void printChain(){
        for(MedicalBlock mb: this.chain){
            System.out.println(mb.printBlock());
        }
    }
    
    public boolean isChainValid() {
    	for(int i = 1; i < this.chain.size(); i++) {
    		MedicalBlock current = this.chain.get(i);
    		MedicalBlock previous = this.chain.get(i - 1);
    		
    		//checking if the hash of the current block is equal to the hash calculation
    		try {
    			if(!current.hash.equals(current.calculateHash())) {
    				return false;
    			}
    		}catch(NoSuchAlgorithmException nsae) {
    			System.out.println("Validation can not be completed because algorithm doesnt exist!");
    		}
    		
    		//checking if the previous hash property is actually equal to the previous hash
    		if(!current.previousHash.equals(previous.hash)) {
    			return false;
    		}
    	}
    	
    	return true;
    	
    }
}
