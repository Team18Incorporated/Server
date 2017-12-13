package Common;

import java.io.Serializable;


public interface ICommand extends Serializable{


	Object execute();

}
