import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n;
    private final boolean[] sites;
    private int openSiteCount = 0;
    private final int virtualTop;
    private final int virtualBottom;
    private WeightedQuickUnionUF uf;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        this.n = n;
        sites = new boolean[n * n];
        virtualTop = n * n;
        virtualBottom = n * n + 1;
        // sites on n-by-n grid + virtual top and virtual bottom
        uf = new WeightedQuickUnionUF(n * n + 2);
    }

    private void validate(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) throw new IllegalArgumentException();
    }

    private int getSiteIndex(int row, int col) {
        // row and column indices are integers between 1 and n
        return n * (row - 1) + (col - 1);
    }

    private void connectNeighbors(int siteIndex) {
        // connect a site to possible 4 neighbors(adjacent sites)
        int possibleNeighbors = 4;
        while (possibleNeighbors > 0) {
            possibleNeighbors--;
            boolean vertical = possibleNeighbors % 2 == 0;
            boolean backward = possibleNeighbors / 2 == 0;
            int distance = (backward ? -1 : 1) * (vertical ? n : 1);
            int neighborSiteIndex = siteIndex + distance;
            boolean sameRow = neighborSiteIndex % n == siteIndex % n;
            boolean sameCol = neighborSiteIndex / n == siteIndex / n;
            boolean outOfBound = neighborSiteIndex < 0 || neighborSiteIndex > n * n - 1;

            if (!sameRow && !sameCol || outOfBound || !sites[neighborSiteIndex])
                continue;

            uf.union(siteIndex, neighborSiteIndex);
        }
    }

    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) return;
        int siteIndex = getSiteIndex(row, col);
        if (row == 1) uf.union(siteIndex, virtualTop);
        if (row == n) uf.union(siteIndex, virtualBottom);

        connectNeighbors(siteIndex);
        sites[siteIndex] = true;
        openSiteCount++;
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return sites[getSiteIndex(row, col)];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        int siteIndex = getSiteIndex(row, col);

        return sites[siteIndex] && uf.connected(siteIndex, virtualTop);
    }

    public int numberOfOpenSites() {
        return openSiteCount;
    }

    public boolean percolates() {
        return uf.connected(virtualTop, virtualBottom);
    }

}
