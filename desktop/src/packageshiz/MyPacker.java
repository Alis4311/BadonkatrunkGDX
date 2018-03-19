package packageshiz;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
public class MyPacker{
    public static void main (String[] args) throws Exception {
        TexturePacker.process("C:/tmp", "C:/tmp/texturepack", "car");
    }
}