package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;



public interface IEntityProcessingService {

    /**
     * @param gameData the current game data etc deltaTime, input and screen information.
     * @param world collection of all entities and their vectors
     * This method processes data in the game. The method is called every update by the game loop.
     */
    void process(GameData gameData, World world);
}
