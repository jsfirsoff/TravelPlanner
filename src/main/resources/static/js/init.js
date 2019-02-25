(function($){
  $(function(){

	// initialize sidenav
    $('.sidenav').sidenav();
    // initialize the collapsible
    $('.collapsible').collapsible();
    // initialize the floating action button
    $('.fixed-action-btn').floatingActionButton();
    
    $('.datepicker').datepicker({format: 'dd/mm/yyyy',
    minDate: new Date()
    }); 
    
    $('.modal').modal();
    $('select').formSelect();
    $('.tooltipped').tooltip();

  }); // end of document ready
})(jQuery); // end of jQuery name space
