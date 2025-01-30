package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConfig;
import entities.Card;
import enums.Status;
import factories.entities.card.ResultSetCardFactory;

public class cardDAO {
    private Connection connection;

    public cardDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Card> findAll() throws Exception {
        List<Card> cards = new ArrayList<Card>();
        String query = "SELECT * FROM todos";

        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Card card = new ResultSetCardFactory(resultSet).createCard();
                cards.add(card);
            }
        }
        return cards;
    }

    public Card findById(Integer id) throws Exception {
        String query = "SELECT * FROM todos WHERE id = ?";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new ResultSetCardFactory(resultSet).createCard();
                } else {
                    throw new Exception("Aucune carte trouvée avec l'ID : " + id);
                }
            }
        }
    }

    public List<Card> findAllNotDone() throws Exception {
        List<Card> cards = new ArrayList<Card>();
        String query = "SELECT * FROM todos WHERE is_done = false";

        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Card card = new ResultSetCardFactory(resultSet).createCard();
                cards.add(card);
            }
        }
        return cards;
    }

    public Boolean create(Card card) throws Exception {
        String query = "INSERT INTO todos (title, status, is_done) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, card.getTitle());
            statement.setString(2, card.getStatus().toString());
            statement.setBoolean(3, card.getIs_done());

            return statement.execute();
        }
    }

    public Boolean update(Card card) throws Exception {
        if (card.getId().isEmpty()) {
            throw new IllegalArgumentException("Impossible de mettre à jour une carte sans ID.");
        }

        String query = "UPDATE todos SET title = ?, status = ?, is_done = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, card.getTitle());
            statement.setString(2, card.getStatus().toString());
            if (card.getStatus() == Status.DONE) {
                card.setIs_done(true);
            }
            statement.setBoolean(3, card.getIs_done());
            statement.setInt(4, card.getId().get());

            return statement.executeUpdate() > 0;
        }
    }

    public Boolean save(Card card) throws Exception {
        return card.getId().isPresent() ? this.update(card) : this.create(card);
    }

    public Boolean delete(Integer id) throws Exception {
        String query = "DELETE FROM todos WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        }
    }
}
