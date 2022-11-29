import java.util.Comparator;

public class Block {
        public Blockfield blockfield;//length,width
    int o = 0;
        public int xPos;
        public int yPos;
        private String hashString;  //当前块的哈希值
        private int hashCode;//??
        private boolean hashCodeCalculated = false;//?
        protected static Comparator<Block> blockComparator = new Comparator<Block>() {

//???
        @Override
        public int compare(Block a, Block b) {
            String aSortCriteria = a.hashString();
            String bSortCriteria = b.hashString();
            return aSortCriteria.compareTo(bSortCriteria);
        }
    };

    public Block (Blockfield blockfield, int xPos, int yPos) {
        this.blockfield = blockfield;
        this.xPos = xPos;
        this.yPos = yPos;
    }


    public Block(Block block, int deltaXPos, int deltaYPos) {
        this.blockfield = block.blockfield;
        this.xPos = block.xPos + deltaXPos;
        this.yPos = block.yPos + deltaYPos;
    }


//用哈希值存当前块的信息
    public String hashString() {
        if (hashString == null) {
            hashString = new StringBuilder(blockfield.blockType.toString()).append(xPos).append(yPos).toString();
        }
        return hashString;
    }

    @Override
    public int hashCode() {
        if (!hashCodeCalculated) {
            hashCode = hashString().hashCode();
            hashCodeCalculated = true;
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (hashString().equals(((Block)obj).hashString())) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
