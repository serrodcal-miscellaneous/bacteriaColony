package problems.bacteriaColony;

public class BacteriaColonyProblem implements IBacteriaColonyProblem {

	private int[][] bacteriaPosition;
	private int size;
	private int transitions;
	
	@Override
	public void setData(int size, int transitions, int[][] bacteriaPosition) {
		if(bacteriaPosition == null){
			throw new IllegalArgumentException();
		}
		this.size = size;
		this.transitions = transitions;
		this.bacteriaPosition = new int[this.size][this.size];
		for(int i = 0; i<this.size; i++){
			for(int j = 0; j<this.size; j++){
					this.bacteriaPosition[i][j] = 0;
			}
		}
		for(int i = 0; i<bacteriaPosition.length; i++){
			this.bacteriaPosition[bacteriaPosition[i][0]][bacteriaPosition[i][1]] = 1;
		}
	}

	@Override
	public void run() {
		for(int i = 0; i<this.transitions; i++){
			solver();
		}
	}
	
	@Override
	public boolean[][] getResult() {
		boolean[][] res = new boolean[this.size][this.size];
		
		for(int i = 0; i<this.size; i++){
			for(int j = 0; j<this.size; j++){
				if(this.bacteriaPosition[i][j] == 1)
					res[i][j] = true;
				else
					res[i][j] = false;
			}
		}
		
		return res;
	}

	private void solver() {
		for(int i = 0; i<this.size; i++){
			for(int j = 0; j<this.size; j++){
				int neighbors = getNeighbors(i, j);
				this.bacteriaPosition[i][j] = getNewValue(i, j, neighbors);
			}
		}
	}
	
	private int getNeighbors(int i, int j){
		int res = 0;
		
		if(i-1 > 0 && i-1 < this.size && j-1 > 0 && j-1< this.size && this.bacteriaPosition[i-1][j-1] == 1){
			res++;
		}else if(i-1 > 0 && i-1 < this.size && j > 0 && j< this.size && this.bacteriaPosition[i-1][j] == 1){
			res++;
		}else if(i-1 > 0 && i-1 < this.size && j+1 > 0 && j+1< this.size && this.bacteriaPosition[i-1][j+1] == 1){
			res++;
		}else if(i > 0 && i < this.size && j-1 > 0 && j-1< this.size && this.bacteriaPosition[i][j-1] == 1){
			res++;
		}else if(i > 0 && i < this.size && j+1 > 0 && j+1< this.size && this.bacteriaPosition[i][j+1] == 1){
			res++;
		}else if(i+1 > 0 && i+1 < this.size && j-1 > 0 && j-1< this.size && this.bacteriaPosition[i+1][j-1] == 1){
			res++;
		}else if(i+1 > 0 && i+1 < this.size && j > 0 && j< this.size && this.bacteriaPosition[i+1][j] == 1){
			res++;
		}else if(i+1 > 0 && i+1 < this.size && j+1 > 0 && j+1< this.size && this.bacteriaPosition[i+1][j+1] == 1){
			res++;
		}
		
		return res;
	}
	
	private int getNewValue(int i, int j, int neighbors) {
		int res = this.bacteriaPosition[i][j];
		
		if(neighbors == 3 && this.bacteriaPosition[i][j] == 0){
			res = 1;
		}else{
			if((neighbors == 1 || neighbors == 0) && this.bacteriaPosition[i][j] == 1){
				res = 0;
			}else{
				if(neighbors>3  && this.bacteriaPosition[i][j] == 1){
					res = 0;
				}else{
					if((neighbors == 1 || neighbors == 0) && this.bacteriaPosition[i][j] == 1){
						res = this.bacteriaPosition[i][j];
					}
				}
			}
		}
		
		return res;
	}
	
}
