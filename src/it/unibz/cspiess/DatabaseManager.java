package it.unibz.cspiess;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by claudio on 27/01/2017.
 */
public class DatabaseManager {

    public static NodeList getRootElements(String fileName, String nodeName) throws ParserConfigurationException, SAXException, IOException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(fileName)).getDocumentElement().getElementsByTagName(nodeName);
    }

    public static Questions loadQuestions() {
        Questions questions = new Questions();
        try {
            NodeList rootElementNodeList = getRootElements("questions.xml", "Question");
            if (rootElementNodeList != null && rootElementNodeList.getLength() > 0) {
                for (int i = 0; i < rootElementNodeList.getLength(); i++) {
                    Element questionElement = (Element) rootElementNodeList.item(i);
                    Question question = new Question();

                    question.setIdentifier(Integer.parseInt(questionElement.getAttribute("id")));

                    NodeList sentenceNodeList = questionElement.getElementsByTagName("Sentence");
                    question.setQuestionWord(sentenceNodeList.item(0).getAttributes().getNamedItem("word").getNodeValue());
                    question.setQuestionSentence(sentenceNodeList.item(0).getFirstChild().getNodeValue());

                    NodeList meaningsNodeList = questionElement.getElementsByTagName("Meanings").item(0).getChildNodes();
                    if (meaningsNodeList != null && meaningsNodeList.getLength() > 0) {
                        for (int j = 0; j < meaningsNodeList.getLength(); j++) {
                            Node meaningNode = meaningsNodeList.item(j);
                            if (meaningNode.getNodeType() == Node.ELEMENT_NODE) {
                                question.addMeaning(meaningsNodeList.item(j).getFirstChild().getNodeValue());
                                if (meaningsNodeList.item(j).getAttributes().getNamedItem("correct") != null &&
                                        meaningsNodeList.item(j).getAttributes().getNamedItem("correct").getNodeValue().equals("true")) {
                                    question.setCorrectMeaning(meaningsNodeList.item(j).getFirstChild().getNodeValue());
                                }
                            }
                        }
                    }

                    NodeList incorrectNodeList = questionElement.getElementsByTagName("Incorrect");
                    if (incorrectNodeList.getLength() > 0) {
                        question.setIncorrectCount(Integer.parseInt(incorrectNodeList.item(0).getAttributes().getNamedItem("count").getNodeValue()));
                    } else
                        question.setIncorrectCount(0);

                    NodeList correctNodeList = questionElement.getElementsByTagName("Correct");
                    if (correctNodeList.getLength() > 0) {
                        question.setCorrectCount(Integer.parseInt(correctNodeList.item(0).getAttributes().getNamedItem("count").getNodeValue()));
                    } else
                        question.setCorrectCount(0);

                    questions.addQuestion(question);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }

    private static int getFirstIntValue(NodeList nodeList, String namedItem) {
        return Integer.parseInt(nodeList.item(0).getAttributes().getNamedItem(namedItem).getNodeValue());
    }

    public static Scoreboards loadScoreboards() {
        Scoreboards scoreboards = new Scoreboards();
        try {
            NodeList scoreboardNodeList = getRootElements("scoreboards.xml", "Scoreboard");
            if (scoreboardNodeList != null && scoreboardNodeList.getLength() > 0) {
                for (int i = 0; i < scoreboardNodeList.getLength(); i++) {
                    Element scoreboardElement = (Element) scoreboardNodeList.item(i);
                    Scoreboard scoreboard = new Scoreboard(scoreboardElement.getAttribute("nickname"));

                    NodeList qsShownNodeList = scoreboardElement.getElementsByTagName("QuestionsShown");
                    scoreboard.setQuestionsShown(getFirstIntValue(qsShownNodeList, "count"));

                    NodeList qsCorrectNodeList = scoreboardElement.getElementsByTagName("QuestionsCorrect");
                    scoreboard.setQuestionsCorrect(getFirstIntValue(qsCorrectNodeList, "count"));

                    NodeList qsTimeSpentNodeList = scoreboardElement.getElementsByTagName("TimeSpent");
                    scoreboard.setTimeSpent(getFirstIntValue(qsTimeSpentNodeList, "count"));

                    NodeList meaningsNodeList = scoreboardElement.getElementsByTagName("WrongQuestions").item(0).getChildNodes();
                    if (meaningsNodeList != null && meaningsNodeList.getLength() > 0) {
                        for (int j = 0; j < meaningsNodeList.getLength(); j++) {
                            Node node = meaningsNodeList.item(j);
                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                scoreboard.addWrongQuestionId(Integer.parseInt(meaningsNodeList.item(j).getAttributes().getNamedItem("id").getNodeValue()));
                            }
                        }
                    }
                    scoreboards.addScoreboard(scoreboard);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoreboards;
    }

    public static void saveScoreboards(Scoreboards scoreboards) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document saveDocument = documentBuilder.newDocument();
            Element scoreboardElement = null;
            Element rootElement = saveDocument.createElement("Scoreboards");

            for (Scoreboard scoreboard : scoreboards.getScoreboards()) {
                scoreboardElement = saveDocument.createElement("Scoreboard");
                scoreboardElement.setAttribute("nickname", scoreboard.getNickname());

                Element questionsShownElement = saveDocument.createElement("QuestionsShown");
                questionsShownElement.setAttribute("count", Integer.toString(scoreboard.getQuestionsShown()));

                Element questionsCorrectElement = saveDocument.createElement("QuestionsCorrect");
                questionsCorrectElement.setAttribute("count", Integer.toString(scoreboard.getQuestionsCorrect()));

                Element timeSpentElement = saveDocument.createElement("TimeSpent");
                timeSpentElement.setAttribute("count", Integer.toString(scoreboard.getTimeSpent()));

                Element wrongQuestionsElement = saveDocument.createElement("WrongQuestions");
                for (int questionId : scoreboard.getWrongQuestionsIds()) {
                    Element questionElement = saveDocument.createElement("Question");
                    questionElement.setAttribute("id", Integer.toString(questionId));
                    wrongQuestionsElement.appendChild(questionElement);
                }

                scoreboardElement.appendChild(questionsShownElement);
                scoreboardElement.appendChild(questionsCorrectElement);
                scoreboardElement.appendChild(timeSpentElement);
                scoreboardElement.appendChild(wrongQuestionsElement);
                rootElement.appendChild(scoreboardElement);
            }

            saveDocument.appendChild(rootElement);

            saveDOMtoFile(saveDocument, "scoreboards.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void saveQuestions(Questions questions) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document saveDocument = documentBuilder.newDocument();
            Element questionElement = null;
            Element rootElement = saveDocument.createElement("Questions");

            for (Question saveQuestion : questions.getQuestions()) {
                questionElement = saveDocument.createElement("Question");
                questionElement.setAttribute("id", Integer.toString(saveQuestion.getIdentifier()));

                Element sentenceElement = saveDocument.createElement("Sentence");
                sentenceElement.setAttribute("word", saveQuestion.getQuestionWord());
                sentenceElement.setTextContent(saveQuestion.getQuestionSentence());

                Element meaningsElement = saveDocument.createElement("Meanings");
                for (String meaningString : saveQuestion.getWordMeanings()) {
                    Element meaningElement = saveDocument.createElement("Meaning");
                    if (meaningString.equals(saveQuestion.getCorrectMeaning()))
                        meaningElement.setAttribute("correct", "true");
                    meaningElement.setTextContent(meaningString);
                    meaningsElement.appendChild(meaningElement);
                }

                Element incorrectCountElement = saveDocument.createElement("Incorrect");
                incorrectCountElement.setAttribute("count", Integer.toString(saveQuestion.getIncorrectCount()));

                Element correctCountElement = saveDocument.createElement("Correct");
                correctCountElement.setAttribute("count", Integer.toString(saveQuestion.getCorrectCount()));

                questionElement.appendChild(sentenceElement);
                questionElement.appendChild(meaningsElement);
                questionElement.appendChild(incorrectCountElement);
                questionElement.appendChild(correctCountElement);
                rootElement.appendChild(questionElement);
            }

            saveDocument.appendChild(rootElement);

            saveDOMtoFile(saveDocument, "questions.xml");
        } catch (Exception pce) {
            pce.printStackTrace();
        }
    }

    private static void saveDOMtoFile(Document dom, String fileName) {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(fileName)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
