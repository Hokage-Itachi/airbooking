
function change_flight_selection() {
    const return_btn = document.getElementById("Return");
    const oneway_btn = document.getElementById("oneway");
    const oneway_label = document.getElementById("oneway-label");
    const return_label = document.getElementById("return-label");
    var  return_date = document.getElementById("datepicker-2");
    if(return_btn.checked == true){
        return_label.style.color = "#0f62ac";

        oneway_label.style.color = "#333";

        document.getElementById("col-ahaft last").style.display="block";
    }
    if(oneway_btn.checked ==true){
        oneway_label.style.color = "#0f62ac";

        return_label.style.color = "#333";

        document.getElementById("col-ahaft last").style.display="none";
    }
}
