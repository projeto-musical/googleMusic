const API_URL = "http://localhost:8080/api/instrumentos";
const API_FAMILIAS = "http://localhost:8080/api/familias";
const API_MARCAS = "http://localhost:8080/api/marcas";
const API_LUTHIERS = "http://localhost:8080/api/luthiers";

document.addEventListener("DOMContentLoaded", () => {

    carregarFamilias();
    carregarMarcas();
    carregarLuthiers();

   
    const form = document.getElementById("form-instrumento");
    if (form) {
        form.addEventListener("submit", salvarInstrumento);
    }
});

async function carregarFamilias() {
    try {
        const res = await fetch(API_FAMILIAS);
        if (!res.ok) throw new Error(`HTTP error! status: ${res.status}`);
        
        const familias = await res.json();
        const selectFamilia = document.getElementById("inst-familia");
        selectFamilia.innerHTML = '<option value="">Selecione...</option>';

        familias.forEach(familia => {
            const option = document.createElement("option");
            
            option.value = familia.idFamilia !== undefined ? familia.idFamilia : familia.id;
            option.textContent = familia.nome;
            selectFamilia.appendChild(option);
        });
    } catch (error) {
        console.error("Erro ao carregar famílias:", error);
    }
}

async function carregarMarcas() {
    try {
        const res = await fetch(API_MARCAS);
        if (!res.ok) throw new Error(`HTTP error! status: ${res.status}`);
        
        const marcas = await res.json();
        const selectMarca = document.getElementById("inst-marca");
        selectMarca.innerHTML = '<option value="">Selecione...</option>';

        marcas.forEach(marca => {
            const option = document.createElement("option");
            option.value = marca.idMarca !== undefined ? marca.idMarca : marca.id;
            option.textContent = marca.nome;
            selectMarca.appendChild(option);
        });
    } catch (error) {
        console.error("Erro ao carregar marcas:", error);
    }
}

async function carregarLuthiers() {
    try {
        const res = await fetch(API_LUTHIERS);
        if (!res.ok) throw new Error(`HTTP error! status: ${res.status}`);

        const luthiers = await res.json();
        const selectLuthier = document.getElementById("inst-luthier");
        selectLuthier.innerHTML = '<option value="">Selecione...</option>';

        luthiers.forEach(luthier => {
            const option = document.createElement("option");
            option.value = luthier.id !== undefined ? luthier.id : luthier.idLuthier;
            option.textContent = luthier.nome;
            selectLuthier.appendChild(option);
        });
    } catch (error) {
        console.error("Erro ao carregar luthiers:", error);
    }
}
async function salvarInstrumento(event) {
    event.preventDefault();

    const idFamiliaVal = parseInt(document.getElementById("inst-familia").value);
    const idMarcaVal = parseInt(document.getElementById("inst-marca").value);
    const idLuthierVal = parseInt(document.getElementById("inst-luthier").value);

    const instrumento = {
        nomeModelo: document.getElementById("inst-nome").value,
        numeroserie: document.getElementById("inst-serie").value,
        anofabricacao: parseInt(document.getElementById("inst-ano").value),
        familia: { idFamilia: idFamiliaVal, id: idFamiliaVal },
        marca: { idMarca: idMarcaVal, id: idMarcaVal },
        luthier: { id: idLuthierVal },
        descricao: document.getElementById("inst-descricao").value
    };

    try {
        const res = await fetch(API_URL, {
            method: "POST",
            headers: { 
                "Content-Type": "application/json" 
            },
            body: JSON.stringify(instrumento)
        });

        if (res.ok) {
            alert("Instrumento cadastrado com sucesso!");
            document.getElementById("form-instrumento").reset();
        } else {
            alert("Erro ao cadastrar instrumento. Verifique os dados fornecidos.");
        }
    } catch (err) {
        alert("Erro de conexão com o servidor: " + err.message);
    }
}

// Limpa o formulário
function resetForm(formId) {
    document.getElementById(formId).reset();
}

// Alterna tema
function toggleTheme() {
    document.body.classList.toggle("dark-theme");
}