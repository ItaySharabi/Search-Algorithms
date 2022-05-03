package NewAPIVersion;

public interface IState {

    public IState getParent();
    public int getDepth();
    public int getWeight();
    public String toString();
}
