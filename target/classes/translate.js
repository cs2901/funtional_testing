function translate(){
    var from = $('#from').val();
    var to = $('#to').val();
    var text = $('#text').val();


    $.ajax({
        url:'http://localhost:8080/translate/'+from+"/"+to+"/"+text,
        type:'GET',
        success: function(response){
            alert(response)
            //$('#action').html(response['statusText']);
        },
        error: function(response){
            alert(response);
        }
    });
}
