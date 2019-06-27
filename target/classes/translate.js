function do_translate() {
    var from = $('#from').val();
    var to = $('#to').val();
    var text = $('#text').val();


    $.ajax({
        url:'http://localhost:8080/translator/'+from+"/"+to+"/"+text,
        type:'GET',
        success: function(response){
            $('#translated').show();
            $('#translated').val(response);
        },
        error: function(response){
            $('#translated').show();
            $('#translated').val(response);
        }
    });
}
