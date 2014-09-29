package problems.bacteriaColony;

public interface IBacteriaColonyProblem {
	public abstract void setData(int size, int transitions, int [][]bacteriaPosition);
	public abstract void run();
	public abstract boolean[][] getResult();
}
