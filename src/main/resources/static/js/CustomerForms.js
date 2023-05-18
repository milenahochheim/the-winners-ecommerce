//CAMPO CEP
function limpa_formulário_cep() {
  //Limpa valores do formulário de cep.
  document.getElementById("rua").value = "";
  document.getElementById("bairro").value = "";
  document.getElementById("cidade").value = "";
  document.getElementById("uf").value = "";
  // document.getElementById("ibge").value = "";
}

function meu_callback(conteudo) {
  if (!("erro" in conteudo)) {
    //Atualiza os campos com os valores.
    document.getElementById("rua").value = conteudo.logradouro;
    document.getElementById("bairro").value = conteudo.bairro;
    document.getElementById("cidade").value = conteudo.localidade;
    document.getElementById("uf").value = conteudo.uf;
    // document.getElementById("ibge").value = conteudo.ibge;
  } //end if.
  else {
    //CEP não Encontrado.
    limpa_formulário_cep();
    alert("CEP não encontrado.");
  }
}
function pesquisacep(valor) {
  //Nova variável "cep" somente com dígitos.
  var cep = valor.replace(/\D/g, "");

  //Verifica se campo cep possui valor informado.
  if (cep != "") {
    //Expressão regular para validar o CEP.
    var validacep = /^[0-9]{8}$/;

    //Valida o formato do CEP.
    if (validacep.test(cep)) {
      //Preenche os campos com "..." enquanto consulta webservice.
      document.getElementById("rua").value = "...";
      document.getElementById("bairro").value = "...";
      document.getElementById("cidade").value = "...";
      document.getElementById("uf").value = "...";
      // document.getElementById("ibge").value = "...";

      //Cria um elemento javascript.
      var script = document.createElement("script");

      //Sincroniza com o callback.
      script.src =
        "https://viacep.com.br/ws/" + cep + "/json/?callback=meu_callback";

      //Insere script no documento e carrega o conteúdo.
      document.body.appendChild(script);
    } //end if.
    else {
      //cep é inválido.
      limpa_formulário_cep();
      alert("Formato de CEP inválido.");
    }
  } //end if.
  else {
    //cep sem valor, limpa formulário.
    limpa_formulário_cep();
  }
}

// -------------------------------------------------------------------------------

//CAMPO DADOS DE COBRANÇA
function limpa_formulário_cep1() {
  //Limpa valores do formulário de cep.
  document.getElementById("rua_1").value = "";
  document.getElementById("bairro_1").value = "";
  document.getElementById("cidade_1").value = "";
  document.getElementById("uf_1").value = "";
  // document.getElementById("ibge").value = "";
}

function meu_callback1(content) {
  if (!("erro" in content)) {
    //Atualiza os campos com os valores.
    document.getElementById("rua_1").value = content.logradouro;
    document.getElementById("bairro_1").value = content.bairro;
    document.getElementById("cidade_1").value = content.localidade;
    document.getElementById("uf_1").value = content.uf;
    // document.getElementById("ibge").value = conteudo.ibge;
  } //end if.
  else {
    //CEP não Encontrado.
    limpa_formulário_cep1();
    alert("CEP não encontrado.");
  }
}
function pesquisacep1(valores) {
  //Nova variável "cep" somente com dígitos.
  var cep_1 = valores.replace(/\D/g, "");

  //Verifica se campo cep possui valor informado.
  if (cep_1 != "") {
    //Expressão regular para validar o CEP.
    var validacep1 = /^[0-9]{8}$/;

    //Valida o formato do CEP.
    if (validacep1.test(cep_1)) {
      //Preenche os campos com "..." enquanto consulta webservice.
      document.getElementById("rua_1").value = "...";
      document.getElementById("bairro_1").value = "...";
      document.getElementById("cidade_1").value = "...";
      document.getElementById("uf_1").value = "...";
      // document.getElementById("ibge").value = "...";

      //Cria um elemento javascript.
      var script = document.createElement("script");

      //Sincroniza com o callback.
      script.src =
        "https://viacep.com.br/ws/" + cep_1 + "/json/?callback=meu_callback";

      //Insere script no documento e carrega o conteúdo.
      document.body.appendChild(script);
    } //end if.
    else {
      //cep é inválido.
      limpa_formulário_cep1();
      alert("Formato de CEP inválido.");
    }
  } //end if.
  else {
    //cep sem valor, limpa formulário.
    limpa_formulário_cep1();
  }
}

//CAMPO CELULAR
function maskTel(celular) {
  if (celular.value.length == 0) celular.value = "(" + celular.value; //quando começamos a digitar, o script irá inserir um parênteses no começo do campo.
  if (celular.value.length == 3) celular.value = celular.value + ") "; //quando o campo já tiver 3 caracteres (um parênteses e 2 números) o script irá inserir mais um parênteses, fechando assim o código de área.

  if (celular.value.length == 10) celular.value = celular.value + "-"; //quando o campo já tiver 8 caracteres, o script irá inserir um tracinho, para melhor visualização do celular.
}

//CAMPO CPF
function maskCPF(cpf) {
  if (cpf.value.length == 3) cpf.value = cpf.value + ".";
  if (cpf.value.length == 7) cpf.value = cpf.value + ".";
  if (cpf.value.length == 11) cpf.value = cpf.value + "-";
}

//CAMPO DATA
function maskDate(date) {
  if (date.value.length == 2) date.value = date.value + "/";
  if (date.value.length == 5) date.value = date.value + "/";
}

//CAMPO DATA CARTÃO
function maskDateCard(dateCard) {
  if (dateCard.value.length == 2) dateCard.value = dateCard.value + "/";
}
