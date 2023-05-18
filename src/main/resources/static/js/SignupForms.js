//Campo celular
function maskTel(celular) {
  if (celular.value.length == 0) celular.value = "(" + celular.value; //quando começamos a digitar, o script irá inserir um parênteses no começo do campo.
  if (celular.value.length == 3) celular.value = celular.value + ") "; //quando o campo já tiver 3 caracteres (um parênteses e 2 números) o script irá inserir mais um parênteses, fechando assim o código de área.

  if (celular.value.length == 10) celular.value = celular.value + "-"; //quando o campo já tiver 8 caracteres, o script irá inserir um tracinho, para melhor visualização do celular.
}

//campo CPFå
function maskCPF(cpf) {
  if (cpf.value.length == 3) cpf.value = cpf.value + ".";
  if (cpf.value.length == 7) cpf.value = cpf.value + ".";
  if (cpf.value.length == 11) cpf.value = cpf.value + "-";
}

