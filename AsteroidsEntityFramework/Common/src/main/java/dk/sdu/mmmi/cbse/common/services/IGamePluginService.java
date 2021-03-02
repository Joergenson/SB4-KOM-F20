package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {


    /**
     * @param gameData the current game data etc deltaTime, input and screen information.
     * @param world collection of all entities and their vectors
     * This method handles the initiation of the the entity it is implemented on. tl;dr code run on game start.
     */
    void start(GameData gameData, World world);

    /**
     * @param gameData the current game data etc deltaTime, input and screen information.
     * @param world collection of all entities and their vectors
     * This method handles the termination of the entity it is implemented on. tl;dr code run on game stop.
     */
    void stop(GameData gameData, World world);
}
