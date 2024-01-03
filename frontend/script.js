const areaDeSaida = document.getElementById("areaDeSaida");
areaDeSaida.textContent = "";

const form = document.forms["formulario"];
form.addEventListener("submit", handleFormSubmition);

function handleFormSubmition(event) {
  areaDeSaida.textContent = "";
  event.preventDefault();

  if (this.idCliente.value > 0) {
    getCliente(this.idCliente.value);
  } else if (this.nome.value !== "" && this.nacionalidade.value !== "") {
    postNovoCliente(this.nome.value, this.nacionalidade.value);
  } else {
    getTodosClientes();
  }
}

function getCliente(idCliente) {
  fetch("http://localhost:8080/clients" + "/" + idCliente + "/client", {
    headers: {
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Methods": "*",
      "Access-Control-Allow-Origin": "*",
    },
  })
    .then((res) => res.json())
    .then((json) => (areaDeSaida.textContent += JSON.stringify(json)));
}

function getTodosClientes() {
  fetch("http://localhost:8080/clients", {
    headers: {
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Methods": "*",
      "Access-Control-Allow-Origin": "*",
    },
  })
    .then((res) => res.json())
    .then((json) => (areaDeSaida.textContent += JSON.stringify(json.clients)));
}

function postNovoCliente(nome, nacionalidade) {
  const body = {
    id: 1,
    client_name: nome,
    nationality: nacionalidade,
  };

  fetch("http://localhost:8080/clients", {
    method: "POST",
    headers: {
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Methods": "*",
      "Access-Control-Allow-Origin": "*",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  })
    .then((res) => res.json())
    .then((result) => console.log(result));
}
