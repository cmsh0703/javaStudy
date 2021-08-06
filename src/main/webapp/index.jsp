
<!DOCTYPE html>
<html>
<body>
<h2>GitHub REST API test</h2>
<script>
alert("asdf");
console.log("asdf");

var request = new XMLHttpRequest();
request.open('GET','https://api.github.com/users/cmsh0703/repos',true);
request.onload = function(){
	var data = JSON.parse(this.response);
	console.log(data);
}

request.send();
</script>
</body>
</html>
