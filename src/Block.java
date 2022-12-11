public class Block {
        public Blockfield blockfield;//length,width
        public int xPos;
        public int yPos;
        public String hashString;  //当前块的哈希值
        public int hashCode;//??
        public boolean hashCodeCalculated = false;//?


    public Block (Blockfield blockfield, int xPos, int yPos) {
        this.blockfield = blockfield;
        this.xPos = xPos;
        this.yPos = yPos;
    }


    public  Block(Block block, int deltaXPos, int deltaYPos) {
        this.blockfield = block.blockfield;
        this.xPos = block.xPos + deltaXPos;
        this.yPos = block.yPos + deltaYPos;
        this.hashString = this.hashString();
        this.hashCode =  this.hashCode();
    }


//用哈希值存当前块的信息
    public String hashString() {
        if (hashString == null) {
            hashString = blockfield.blockType.toString() + xPos + yPos + blockfield.number;
        }
        return hashString;
    }

    @Override
    public int hashCode() {
        if (!hashCodeCalculated) {
            hashCode = hashString().hashCode() & Integer.MAX_VALUE;
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
}
