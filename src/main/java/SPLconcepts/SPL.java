package SPLconcepts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.repodriller.domain.Commit;

public class SPL {
	
	public ArrayList<CoreAssetBaseline> coreAssetBaselines = new ArrayList<CoreAssetBaseline>();
	public ArrayList<ProductPortfolio> productPortfolios = new ArrayList<ProductPortfolio>();
	
	
	
	public List<String> getCoreAssetBaselinesAsCommitsHashes(){
		ArrayList<String> commitList = new ArrayList <String>();
		
		for (int i=0; i< coreAssetBaselines.size() ;i++){
			commitList.add(coreAssetBaselines.get(i).getCommit().getHash());
		}
		
		return commitList;
	}
	
	public CoreAssetBaseline getCoreAssetBaselineFromCommit (Commit co){
		Iterator<CoreAssetBaseline> it = coreAssetBaselines.iterator();
		while (it.hasNext()){
			CoreAssetBaseline ca = it.next();
			if (ca.getCommit() == co)
				return ca;
		}
		return null;
	}
	
	public void setCoreAssetBaselines(ArrayList<CoreAssetBaseline> baselines){
		this.coreAssetBaselines=baselines;
	}
	
	public void addBaseline(CoreAssetBaseline baseline){
		coreAssetBaselines.add(baseline);
	}
	
	public int getCoreAssetBaselineSize(){
		return coreAssetBaselines.size();
	}
	
	public int getProductPortfoliosSize(){
		return productPortfolios.size();
	}


	public CoreAssetBaseline getCoreAssetBaseline(int i) {
		
		return coreAssetBaselines.get(i);
	}
	
}