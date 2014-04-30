import com.drravns.gowars.world.BaseWorld;
import greenfoot.Greenfoot;
import greenfoot.World;

/**
 * Created by Kitty on 4/29/2014.
 */
public class DefaultWorld extends World {
    public DefaultWorld() {
        super(1024, 768, 1);
        Greenfoot.setWorld(new BaseWorld());
    }
}
