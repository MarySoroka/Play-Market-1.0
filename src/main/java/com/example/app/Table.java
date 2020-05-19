package com.example.app;

import com.example.model.game.Game;
import com.example.model.game.GameType;
import com.example.model.game.GamesList;
import com.example.model.game.OnlineStatus;
import com.example.model.game.gameEngine.GraphicsMode;
import com.example.model.skillAndActionGame.Mode;
import com.example.model.skillAndActionGame.SkillActionGame;
import com.example.model.skillAndActionGame.combatGame.CombatGame;
import com.example.model.skillAndActionGame.mazeGame.MazeGame;
import com.example.model.skillAndActionGame.raceGame.RaceGame;
import com.example.model.strategy.educationalGame.EducationalGame;
import com.example.model.strategy.gameOfChance.GameOfChance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Date;


public class Table extends JFrame {
    private static final long serialVersionUID = 1L;
    private String[] columns = {"Type", "Name", "Company",
            "Release date", "Graphics", "Online status", "Number of players", "Mode"};

    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

    private Object[][] data;



    private JComboBox<String> gameTypes = new JComboBox<String>(GameType.getListOfTypes());
    private JComboBox<String> graphicsTypes = new JComboBox<String>(GraphicsMode.getListOfTypes());
    private JComboBox<String> onlineStatus = new JComboBox<String>(OnlineStatus.getListOfTypes());
    private JComboBox<Integer> numberOfPlayers = new JComboBox<Integer>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    private JComboBox<String> modeType = new JComboBox<String>(Mode.getListOfTypes());

    public DefaultTableModel getModel() {
        return model;
    }

    private DefaultTableModel model;

    public JTable getTable() {
        return table;
    }

    private JTable table;

    private DefaultCellEditor type = new DefaultCellEditor(gameTypes);
    private DefaultCellEditor graphics = new DefaultCellEditor(graphicsTypes);
    private DefaultCellEditor status = new DefaultCellEditor(onlineStatus);
    private DefaultCellEditor number = new DefaultCellEditor(numberOfPlayers);
    private DefaultCellEditor mode = new DefaultCellEditor(modeType);

    private GamesList gamesList;

    public GamesList getGamesList() {
        return gamesList;
    }

    public void setGamesList(GamesList gamesList) {
        this.gamesList = gamesList;
    }

    public Table(ArrayList<Game> games) {
        setGamesList(new GamesList(games));
    }

    public JTable createTable() {
        setData(initialize());
        this.model = new DefaultTableModel(data, columns) {
            private static final long serialVersionUID = 1L;
            public Class<?> getColumnClass(int column) {
                return data[0][column].getClass();
            }
        };

        // Создание таблицы
        this.table = new JTable(model);
        // Определение редактора ячеек
        table.setDefaultEditor(Date.class, new DateCellEditor());
        table.getColumnModel().getColumn(0).setCellEditor(type);
        table.getColumnModel().getColumn(4).setCellEditor(graphics);
        table.getColumnModel().getColumn(5).setCellEditor(status);
        table.getColumnModel().getColumn(6).setCellEditor(number);
        table.getColumnModel().getColumn(7).setCellEditor(mode);
        return table;
    }

    public void addRow(Game o){
        this.gamesList.getGames().add(o);
    }
    public void deleteRow(String name){
        this.gamesList.getGames().removeIf(g -> g.getName().equals(name));
    }

    public Game getByName(String name){
        for (Game g:
             this.gamesList.getGames()) {
            if (g.getName().equals(name)){
                return g;
            }
        }
        return null;
    }

    public Object[][] initialize() {
        //   setGamesList(new GamesList("/home/maria/Desktop/oop/games"));

        Object[][] games = new Object[this.gamesList.getGames().size()][8];
        int i = 0;
        for (Game g : gamesList.getGames()) {
            games[i][0] = g.getType();
            games[i][1] = g.getName();
            games[i][2] = g.getAuthor();
            games[i][3] = g.getReleaseDate();
            games[i][4] = g.getGraphics().getGraphicsMode().getType();
            games[i][5] = g.getOnlineStatus().toString().toLowerCase();
            if (g.getClass().equals(CombatGame.class)) {
                games[i][6] = ((SkillActionGame) g).getMaxNumberOfPlayers();
                games[i][7] = ((CombatGame) g).getMode().toString().toLowerCase();
            } else if (g.getClass().equals(MazeGame.class)) {
                games[i][6] = ((SkillActionGame) g).getMaxNumberOfPlayers();
                games[i][7] = ((MazeGame) g).getMode().toString().toLowerCase();
            } else if (g.getClass().equals(RaceGame.class)) {
                games[i][6] = ((SkillActionGame) g).getMaxNumberOfPlayers();
                games[i][7] = "-";
            } else if (g.getClass().equals(EducationalGame.class)) {
                games[i][6] = "-";
                games[i][7] = ((EducationalGame) g).getMode().toString().toLowerCase();
            } else if (g.getClass().equals(GameOfChance.class)) {
                games[i][6] = "-";
                games[i][7] = ((GameOfChance) g).getMode().toString().toLowerCase();
            }
            i++;
        }
        return games;
    }
}
