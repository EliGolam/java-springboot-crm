package com.example.application.views.list;

import com.example.application.data.entity.Contact;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Contacts | Springboot CRM")
@Route(value = "") // Default route
public class ListView extends VerticalLayout {

  Grid<Contact> grid = new Grid<>(Contact.class); 
  TextField searchField = new TextField();

  public ListView() {
    addClassName("list-view"); 
    setSizeFull();

    configureGrid(); 


    // ADD
    add(
      getToolbar(),
      grid
    );
  }


  // METHODS


  private Component getToolbar() {
    this.searchField.setPlaceholder("Search by name...");
    this.searchField.setClearButtonVisible(true);
    this.searchField.setValueChangeMode(ValueChangeMode.LAZY); // Lazy search

    Button addContactBtn = new Button("Add contact");

    HorizontalLayout contactListHeader = new HorizontalLayout(searchField, addContactBtn);
    contactListHeader.addClassName("contact-list-header");
    return contactListHeader;
  }

  private void configureGrid() {
    grid.addClassName("contact-grid");
    grid.setSizeFull();
    grid.setColumns("firstName", "lastName", "email"); 

    grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status"); 
    grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
    
    // resizing Grid
    grid.getColumns().forEach(col -> col.setAutoWidth(true));
  }
}
