package UI.Controller;
import javafx.scene.control.Tab;

/**
 * Abstract controller for tabs, with access to Tab view element
 */
public abstract class TabController extends PaneController{
    private Tab tab;

    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }
}
