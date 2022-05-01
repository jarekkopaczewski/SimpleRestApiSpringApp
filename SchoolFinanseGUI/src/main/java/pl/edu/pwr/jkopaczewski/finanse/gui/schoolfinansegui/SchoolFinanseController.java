package pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.dto.EventDto;
import pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.dto.PaymentDto;
import pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.dto.PersonDto;
import pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.rest.EventRestClient;
import pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.rest.PaymentRestClient;
import pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.rest.PersonRestClient;
import pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.table.EventTableModel;
import pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.table.PaymentTableModel;
import pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.table.PersonTableModel;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SchoolFinanseController implements Initializable {
    private ObservableList<EventTableModel> eventsData;
    private ObservableList<PersonTableModel> personData;
    private ObservableList<PaymentTableModel> paymentsData;
    private List<EventDto> events;
    private List<PersonDto> people;
    private List<PaymentDto> payments;
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @FXML
    private TableView<EventTableModel> eventsTableView;
    @FXML
    private TableView<PaymentTableModel> paymentsTableView;
    @FXML
    private TableView<PersonTableModel> personTableView;
    @FXML
    private Button refreshButton;
    @FXML
    private TextField personName;
    @FXML
    private TextField personSurname;
    @FXML
    private TextField eventName;
    @FXML
    private TextField eventPlace;
    @FXML
    private TextField eventDate;
    @FXML
    private TextField paymentDeadline;
    @FXML
    private TextField paymentAmount;
    @FXML
    private TextField paymentNumber;
    @FXML
    private TextField paymentPersonId;
    @FXML
    private TextField paymentEventId;

    @FXML
    protected void onPayButtonClick() {
        PaymentRestClient paymentRestClient = new PaymentRestClient();
        Thread thread = new Thread(() -> {
            Calendar cal = Calendar.getInstance();
            paymentRestClient.payInstallment(Math.toIntExact(paymentsTableView.getSelectionModel().getSelectedItem().getPaymentId()),
                    dateFormat.format(cal.getTime()));
        });
        thread.start();
        try {
            thread.join();
            loadEventData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onAddEventButtonClick() {
        EventDto eventDto = new EventDto();
        eventDto.setName(eventName.getText());
        eventDto.setPlace(eventPlace.getText());
        eventDto.setDate(eventDate.getText());
        EventRestClient eventRestClient = new EventRestClient();
        Thread thread = new Thread(() -> {
            eventRestClient.addEvent(eventDto);
            loadEventData();
        });
        thread.start();
    }

    @FXML
    protected void onAddPersonButtonClick() {
        PersonDto dto = new PersonDto();
        dto.setName(personName.getText());
        dto.setSurname(personSurname.getText());
        PersonRestClient personRestClient = new PersonRestClient();
        Thread thread = new Thread(() -> {
            personRestClient.addPerson(dto);
            loadPersonData();
        });
        thread.start();
    }


    @FXML
    protected void onRefreshButtonClick() {
        loadEventData();
        loadPersonData();
        loadPaymentData();
    }

    private void loadEventData() {
        EventRestClient eventRestClient = new EventRestClient();
        Thread thread = new Thread(() -> {
            events = eventRestClient.getEvents();
            eventsData.clear();
            eventsData.addAll(events.stream().map(EventTableModel::of).collect(Collectors.toList()));
            eventsTableView.setItems(eventsData);
        });
        thread.start();
    }

    private void loadPersonData() {
        PersonRestClient personRestClient = new PersonRestClient();
        Thread thread = new Thread(() -> {
            people = personRestClient.getPeople();
            personData.clear();
            personData.addAll(people.stream().map(PersonTableModel::of).collect(Collectors.toList()));
            personTableView.setItems(personData);
        });
        thread.start();
    }

    private void loadPaymentData() {
        PaymentRestClient paymentRestClient = new PaymentRestClient();
        Thread thread = new Thread(() -> {
            payments = paymentRestClient.getPayments();
            paymentsData.clear();
            paymentsData.addAll(payments.stream().map(PaymentTableModel::of).collect(Collectors.toList()));
            paymentsTableView.setItems(paymentsData);
        });
        thread.start();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        eventsData = FXCollections.observableArrayList();

        personTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        personData = FXCollections.observableArrayList();

        paymentsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        paymentsData = FXCollections.observableArrayList();

        // event
        TableColumn idColumn = new TableColumn("eventId");
        idColumn.setCellValueFactory(new PropertyValueFactory<EventTableModel, String>("eventId"));

        TableColumn nameColumn = new TableColumn("name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<EventTableModel, String>("name"));

        TableColumn placeColumn = new TableColumn("place");
        placeColumn.setCellValueFactory(new PropertyValueFactory<EventTableModel, String>("place"));

        TableColumn dateColumn = new TableColumn("date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<EventTableModel, String>("date"));

        // person
        TableColumn personIdColumn = new TableColumn("personId");
        personIdColumn.setCellValueFactory(new PropertyValueFactory<PersonTableModel, String>("personId"));

        TableColumn personName = new TableColumn("name");
        personName.setCellValueFactory(new PropertyValueFactory<PersonTableModel, String>("name"));

        TableColumn personSurName = new TableColumn("surname");
        personSurName.setCellValueFactory(new PropertyValueFactory<PersonTableModel, String>("surname"));

        // payment
        TableColumn paymentId = new TableColumn("paymentId");
        paymentId.setCellValueFactory(new PropertyValueFactory<PaymentTableModel, String>("paymentId"));

        TableColumn paymentDeadline = new TableColumn("paymentDeadline");
        paymentDeadline.setCellValueFactory(new PropertyValueFactory<PaymentTableModel, String>("paymentDeadline"));

        TableColumn payday = new TableColumn("payday");
        payday.setCellValueFactory(new PropertyValueFactory<PaymentTableModel, String>("payday"));

        TableColumn paymentAmount = new TableColumn("paymentAmount");
        paymentAmount.setCellValueFactory(new PropertyValueFactory<PaymentTableModel, String>("paymentAmount"));

        TableColumn instalmentNumber = new TableColumn("instalmentNumber");
        instalmentNumber.setCellValueFactory(new PropertyValueFactory<PaymentTableModel, String>("instalmentNumber"));

        TableColumn payed = new TableColumn("isPayed");
        payed.setCellValueFactory(new PropertyValueFactory<PaymentTableModel, String>("isPayed"));

        TableColumn eventId = new TableColumn("eventId");
        eventId.setCellValueFactory(new PropertyValueFactory<PaymentTableModel, String>("eventId"));

        TableColumn personId = new TableColumn("personId");
        personId.setCellValueFactory(new PropertyValueFactory<PaymentTableModel, String>("personId"));

        eventsTableView.getColumns().addAll(idColumn, nameColumn, placeColumn, dateColumn);
        personTableView.getColumns().addAll(personIdColumn, personName, personSurName);
        paymentsTableView.getColumns().addAll(paymentId, paymentDeadline, payday, paymentAmount, instalmentNumber, payed, eventId, personId);

        loadEventData();
        loadPersonData();
        loadPaymentData();
    }

    @FXML
    protected void onHelloButtonClick() {
        loadEventData();
        eventsTableView.setItems(eventsData);
    }

    @FXML
    protected void onAddPaymentsButtonClick() {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(dateFormat.parse(paymentDeadline.getText()));
        } catch (ParseException e) {
            cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, 1);
            e.printStackTrace();
        }
        for (int i = 1; i <= Long.parseLong(paymentNumber.getText()); i++) {

            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setPaymentDeadline(dateFormat.format(cal.getTime()));
            paymentDto.setPaymentAmount(Long.parseLong(paymentAmount.getText()));
            paymentDto.setInstalmentNumber(i);

            PersonDto personDto = new PersonDto();
            personDto.setPersonId(Long.parseLong(paymentPersonId.getText()));
            paymentDto.setPaymentPerson(personDto);

            EventDto eventDto = new EventDto();
            eventDto.setEventId(Long.parseLong(paymentEventId.getText()));
            paymentDto.setPaymentEvent(eventDto);

            PaymentRestClient paymentRestClient = new PaymentRestClient();
            Thread thread = new Thread(() -> {
                paymentRestClient.addPayments(paymentDto);
            });
            thread.start();
            cal.add(Calendar.MONTH, 1);
        }
        loadPaymentData();
    }
}