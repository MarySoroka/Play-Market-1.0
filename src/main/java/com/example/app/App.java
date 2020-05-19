package com.example.app;

import com.example.controller.runController.*;
import com.example.controller.serializationController.SerializationReadController;
import com.example.controller.serializationController.SerializationWriteController;
import com.example.model.ArchiveType;
import com.example.model.game.Game;
import com.example.model.game.GameType;
import com.example.model.game.OnlineStatus;
import com.example.model.game.gameEngine.GraphicsMode;
import com.example.model.serializer.*;
import com.example.model.skillAndActionGame.Mode;
import com.example.model.strategy.educationalGame.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

public class App extends JFrame {
    private JButton addButton = new JButton("add");
    private JButton safeButton = new JButton("safe");
    private JButton runButton = new JButton("run");
    private JButton deleteButton = new JButton("delete");
    private static String PATH_TO_GRADLE_PROJECT = "./";
    private static String GRADLEW_EXECUTABLE = "gradlew.bat";
    private static String BALNK = " ";


    private JComboBox<String> gameTypes = new JComboBox<String>(GameType.getListOfTypes());
    private JComboBox<String> graphicsTypes = new JComboBox<String>(GraphicsMode.getListOfTypes());
    private JComboBox<String> onlineStatus = new JComboBox<String>(OnlineStatus.getListOfTypes());
    private JComboBox<Integer> numberOfPlayers = new JComboBox<Integer>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    private JComboBox<String> mode = new JComboBox<String>(Mode.getListOfTypes());
    private RunController runController;

    public void setName(JTextField name) {
        this.name = name;
    }

    public JTextField getDate() {
        return date;
    }

    public void setDate(JTextField date) {
        this.date = date;
    }

    private JTextField name = new JTextField("Game's name");
    private JTextField company = new JTextField("Company");
    private JTextField date = new JTextField("Release date(dd/mm/yyyy)");


    public App() {
        try (BufferedReader br = new BufferedReader(new FileReader("/home/maria/Desktop/play/archive/type.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if (!"none".equals(line)) {
                    ToolingAPI.execute("un" + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        SerializerType serializerType = chooseSerializer();
        SerializationReadController serializationReadController = new SerializationReadController(getSerializers());
        Table table = null;
        try {
            table = new Table(serializationReadController.serialize(serializerType));
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            JPanel buttonGroup = new JPanel();
            buttonGroup.add(addButton);
            buttonGroup.add(safeButton);
            buttonGroup.add(runButton);
            buttonGroup.add(deleteButton);

            JPanel comboGroup = new JPanel();
            name.setFont(name.getFont().deriveFont(18f));
            company.setFont(name.getFont().deriveFont(18f));
            date.setFont(name.getFont().deriveFont(18f));
            comboGroup.add(gameTypes);
            comboGroup.add(name);
            comboGroup.add(company);
            comboGroup.add(date);
            comboGroup.add(graphicsTypes);
            comboGroup.add(onlineStatus);
            comboGroup.add(numberOfPlayers);
            comboGroup.add(mode);
            comboGroup.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 10));

            getContentPane().add(new JScrollPane(table.createTable()), BorderLayout.NORTH);
            getContentPane().add(comboGroup, BorderLayout.WEST);
            getContentPane().add(buttonGroup, BorderLayout.SOUTH);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException | ParseException | IOException e) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                    "Can't load games",
                    "Input error",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }

        runController = new RunController(getCommands());
        Table finalTable = table;
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Object[] objects = new Object[8];
                    objects[0] = gameTypes.getItemAt(gameTypes.getSelectedIndex());
                    objects[1] = name.getText();
                    objects[2] = company.getText();
                    objects[3] = new SimpleDateFormat("dd/mm/yyyy").parse(date.getText());
                    objects[4] = graphicsTypes.getItemAt(graphicsTypes.getSelectedIndex());
                    objects[5] = onlineStatus.getItemAt(onlineStatus.getSelectedIndex());
                    objects[6] = numberOfPlayers.getItemAt(numberOfPlayers.getSelectedIndex());
                    objects[7] = mode.getItemAt(mode.getSelectedIndex());
                    switch (Objects.requireNonNull(GameType.getByName(gameTypes.getItemAt(gameTypes.getSelectedIndex())))) {
                        case RACE:
                            objects[7] = "-";
                            break;
                        case EDUCATIONAL:
                        case CHANCE:
                            objects[6] = "-";
                            break;
                    }
                    finalTable.getModel().addRow(objects);
                    finalTable.getGamesList().addNewGame(new String[]{
                            objects[0].toString(),
                            objects[1].toString(),
                            objects[2].toString(),
                            date.getText(),
                            objects[4].toString(),
                            objects[5].toString(),
                            objects[6].toString(),
                            objects[7].toString()
                    });
                } catch (ParseException e) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,
                            "All fields should be full correct",
                            "Input error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        Table finalTable1 = table;
        safeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SerializerType serializerType = chooseSerializer();
                LinkedList<Object> list = new LinkedList<>();
                for (int i = 0; i < finalTable1.getModel().getRowCount(); i++) {
                    for (int j = 0; j < finalTable1.getModel().getColumnCount(); j++) {
                        list.add(finalTable1.getModel().getValueAt(i, j));
                    }
                }
                SerializationWriteController serializationWriteController = new SerializationWriteController(getSerializers());
                try {
                    serializationWriteController.serialize(serializerType, finalTable1.getGamesList().getGames());
                } catch (IOException e) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,
                            "Can't save games",
                            "Output error",
                            JOptionPane.ERROR_MESSAGE);
                    System.out.println(e.getMessage());
                }
                ArchiveType archiveType = chooseArchive();
                if (archiveType != ArchiveType.NONE) {
                    ToolingAPI.execute(archiveType.name().toLowerCase());
                }
                FileWriter f0 = null;
                try {
                    f0 = new FileWriter("/home/maria/Desktop/play/archive/type.txt");
                    f0.write(archiveType.name().toLowerCase());
                    f0.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        Table finalTable2 = table;
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                finalTable2.getModel().removeRow(finalTable2.getTable().getSelectedRow());

            }
        });
        Table finalTable3 = table;
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Game g = finalTable3.getByName(finalTable3.getModel().getValueAt(
                        finalTable3.getTable().getSelectedRow(), 1).toString());
                runController.runSpecificGame(g);
            }
        });
        setSize(1000, 1000);
        setVisible(true);
    }


    public SerializerType chooseSerializer() {
        JFrame frame = new JFrame();
        Object[] possibilities = SerializerType.values();
        return (SerializerType) JOptionPane.showInputDialog(
                frame,
                "Serialization",
                "Choose Serializer",
                JOptionPane.PLAIN_MESSAGE, null,
                possibilities,
                Subject.values()[0]);

    }

    public ArchiveType chooseArchive() {
        JFrame frame = new JFrame();
        Object[] possibilities = ArchiveType.values();
        return (ArchiveType) JOptionPane.showInputDialog(
                frame,
                "Archive",
                "Choose archive",
                JOptionPane.PLAIN_MESSAGE, null,
                possibilities,
                Subject.values()[0]);

    }

    public Map<SerializerType, SerializerFactory> getSerializers() {
        SerializerFactory binary = new BinarySerializer();
        SerializerFactory xml = new XMLSerializer();
        SerializerFactory specific = null;
        try {
            specific = new SpecificSerializer();
        } catch (FileNotFoundException e) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                    "Can't find file",
                    "Input error",
                    JOptionPane.ERROR_MESSAGE);
        }
        Map<SerializerType, SerializerFactory> serializer = new EnumMap<SerializerType, SerializerFactory>(SerializerType.class);
        serializer.put(SerializerType.BINARY, binary);
        serializer.put(SerializerType.XML, xml);
        serializer.put(SerializerType.SPECIFIC, specific);
        return serializer;
    }

    public Map<GameType, RunCommandFactory> getCommands() {
        RunCommandFactory runCombat = new RunCombatGame();
        RunCommandFactory runRace = new RunRaceGame();
        RunCommandFactory runMaze = new RunMazeGame();
        RunCommandFactory runChance = new RunGameOfChance();
        RunCommandFactory runEducational = new RunEducationalGame();
        Map<GameType, RunCommandFactory> commands = new EnumMap<GameType, RunCommandFactory>(GameType.class);
        commands.put(GameType.COMBAT, runCombat);
        commands.put(GameType.RACE, runRace);
        commands.put(GameType.MAZE, runMaze);
        commands.put(GameType.CHANCE, runChance);
        commands.put(GameType.EDUCATIONAL, runEducational);
        return commands;
    }

    public static void main(String[] args) {
        new App();
    }
}