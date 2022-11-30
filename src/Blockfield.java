public class Blockfield {
    //    块的一些属性，与type相对应
    public BlockType blockType;
    public int[] number;
    public int width;
    public int height;
    public Blockfield(BlockType blockType){
        this.blockType = blockType;
        switch (blockType) {
            case SQUARE:
                width = 2;
                height = 2;
                break;
            case HORIZONTAL:
                width = 2;
                height = 1;
                break;
            case VERTICAL:
                width = 1;
                height = 2;
                break;
            case SINGLE:
                width = 1;
                height = 1;
                break;
            case BLANK:
                width = 1;
                height = 1;
                break;
        }
    }
}
