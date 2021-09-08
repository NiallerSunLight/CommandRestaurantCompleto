package co.edu.unicauca.commandrestaurant.domain;

import co.edu.unicauca.commandrestaurant.access.IFoodRepository;
import co.edu.unicauca.commandrestaurant.access.RepositoryFactory;
import co.edu.unicauca.commandrestaurant.domain.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Comando concreto para eliminar una comida
  * @author Jesus Edwin Adrada Ruiz - jesusadrada@unicauca.edu.co
  *         Carlos Enrique Hoyos - joiroce@unicauca.edu.co
  *         Eliana Andrea Camayo - eacamayo@unicauca.edu.co
 */
public class DeleteCommand  extends Command{
    
    /**
    * Comida a Eliminar
    */
    private Food food;
    /**
     * Instancia al receptor
     */
    private FoodService service;
    
    public DeleteCommand(Food food){
       this.food = food;
       IFoodRepository repo = RepositoryFactory.getInstance().getRepository("default");
       service = new FoodService(repo);
       this.hasUndo = true;
    }
    @Override
    public void execute() {
        Logger logger= LoggerFactory.getLogger(DeleteCommand.class); 
        logger.info("Comando de Eliminacion ejecutado. Se elimino la comida " + food.toString());
        service.delete(food.getId());
    }

    @Override
    public void undo() {
        Logger logger= LoggerFactory.getLogger(CreateCommand.class); 
        logger.info("Comando de eliminacion se ha deshecho. Se creo la comida " + food.toString());
        service.create(food);
    }
    
}
