import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ChatBotGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    private Map<String, String> faqResponses;

    public ChatBotGUI() {
        setTitle("AI Chatbot");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        sendButton = new JButton("Send");

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setupFAQResponses();

        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());
    }

    private void setupFAQResponses() {
        faqResponses = new HashMap<>();
        faqResponses.put("hello", "Hi there! How can I help you?");
        faqResponses.put("hi", "Hello! Need any assistance?");
        faqResponses.put("how are you", "I'm just a bot, but I'm functioning properly!");
        faqResponses.put("what is your name", "I'm a simple AI chatbot created in Java.");
        faqResponses.put("bye", "Goodbye! Have a great day!");
        faqResponses.put("what are the libraries of java", "Java has a rich set of libraries that help developers build applications quickly and efficiently.");
        faqResponses.put("thank you", "You're welcome!");
        faqResponses.put("what is java", "Java is a high-level, object-oriented programming language developed by Sun Microsystems in 1995.");
        faqResponses.put("help", "I can answer simple questions. Try saying 'hello' or 'what is your name'.");
    }

    private void sendMessage() {
        String userText = inputField.getText().trim();
        if (userText.isEmpty()) return;

        chatArea.append("You: " + userText + "\n");
        String response = generateResponse(userText);
        chatArea.append("Bot: " + response + "\n");

        inputField.setText("");
    }

    private String generateResponse(String input) {
        input = input.toLowerCase();

        for (String keyword : faqResponses.keySet()) {
            if (input.contains(keyword)) {
                return faqResponses.get(keyword);
            }
        }

        return "I'm not sure how to respond to that. Try asking something else!";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChatBotGUI().setVisible(true);
        });
    }
}

