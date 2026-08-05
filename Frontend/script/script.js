const API_URL = 'http://localhost:8080/api';

// --- CONTROLE DE TEMA E FORMULÁRIO ---

function toggleTheme() {
    document.body.classList.toggle('dark-theme');
}

function resetForm(formId) {
    const form = document.getElementById(formId);
    if (form) {
        form.reset();
        const hiddenInput = form.querySelector('input[type="hidden"]');
        if (hiddenInput) hiddenInput.value = '';
    }
}

// --- INICIALIZAÇÃO (EVENT LISTENERS) ---

document.addEventListener('DOMContentLoaded', () => {
    // 1. Se estiver no cadastro de Instrumento, carrega as Opções de Marca e Luthier
    if (document.getElementById('inst-marca') && document.getElementById('inst-luthier')) {
        carregarSelectsInstrumento();
    }

    // 2. Listeners de Submissão dos Formulários
    const formMarca = document.getElementById('form-marca');
    if (formMarca) {
        formMarca.addEventListener('submit', cadastrarMarca);
    }

    const formLuthier = document.getElementById('form-luthier');
    if (formLuthier) {
        formLuthier.addEventListener('submit', cadastrarLuthier);
    }

    const formInstrumento = document.getElementById('form-instrumento');
    if (formInstrumento) {
        formInstrumento.addEventListener('submit', cadastrarInstrumento);
    }

    // 3. Se estiver na página de listagem (index.html), carrega a tabela
    if (document.getElementById('instrumentos-list')) {
        carregarInstrumentos();
    }
});


// ==========================================
// 1. CADASTRO DE MARCA
// ==========================================

async function cadastrarMarca(event) {
    event.preventDefault();

    const id = document.getElementById('marca-id').value;
    const nome = document.getElementById('marca-nome').value;
    const paisOrigem = document.getElementById('marca-origem').value;

    const payload = { nome, paisOrigem };
    if (id) payload.idMarca = parseInt(id);

    try {
        const response = await fetch(`${API_URL}/marcas`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (response.ok) {
            alert('Marca salva com sucesso!');
            resetForm('form-marca');
        } else {
            alert('Erro ao salvar a marca no banco de dados.');
        }
    } catch (error) {
        console.error('Erro ao cadastrar marca:', error);
        alert('Erro ao conectar com o servidor backend.');
    }
}


// ==========================================
// 2. CADASTRO DE LUTHIER
// ==========================================

async function cadastrarLuthier(event) {
    event.preventDefault();

    const id = document.getElementById('luthier-id').value;
    const nome = document.getElementById('luthier-nome').value;
    const telefone = document.getElementById('luthier-telefone').value;
    const especialidade = document.getElementById('luthier-especialidade').value;

    const payload = { nome, telefone, especialidade };
    if (id) payload.id = parseInt(id);

    try {
        const response = await fetch(`${API_URL}/luthiers`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (response.ok) {
            alert('Luthier salvo com sucesso!');
            resetForm('form-luthier');
        } else {
            alert('Erro ao salvar o luthier no banco de dados.');
        }
    } catch (error) {
        console.error('Erro ao cadastrar luthier:', error);
        alert('Erro ao conectar com o servidor backend.');
    }
}


// ==========================================
// 3. CADASTRO DE INSTRUMENTO
// ==========================================

async function carregarSelectsInstrumento() {
    try {
        // Carrega Marcas (usa m.idMarca conforme Marca.java)
        const resMarcas = await fetch(`${API_URL}/marcas`);
        const marcas = await resMarcas.json();
        const selectMarca = document.getElementById('inst-marca');
        selectMarca.innerHTML = '<option value="">Selecione...</option>';
        marcas.forEach(m => {
            selectMarca.innerHTML += `<option value="${m.idMarca || m.id}">${m.nome}</option>`;
        });

        // Carrega Luthiers (usa l.id conforme Luthier.java)
        const resLuthiers = await fetch(`${API_URL}/luthiers`);
        const luthiers = await resLuthiers.json();
        const selectLuthier = document.getElementById('inst-luthier');
        selectLuthier.innerHTML = '<option value="">Selecione...</option>';
        luthiers.forEach(l => {
            selectLuthier.innerHTML += `<option value="${l.id}">${l.nome}</option>`;
        });
    } catch (error) {
        console.error('Erro ao carregar opções de Marca e Luthier:', error);
    }
}
// Na função cadastrarInstrumento:
async function cadastrarInstrumento(event) {
    event.preventDefault();

    const id = document.getElementById('inst-id')?.value;
    const nomeModelo = document.getElementById('inst-nome').value;
    const familiaVal = document.getElementById('inst-familia').value;
    const anoFabricacao = document.getElementById('inst-ano').value;
    const descricao = document.getElementById('inst-descricao')?.value || '';
    const marcaId = document.getElementById('inst-marca').value;
    const luthierId = document.getElementById('inst-luthier').value;

    const payload = {
        nomeModelo: nomeModelo,
        familia: familiaVal.toUpperCase(), // Força "SOPRO", "CORDAS", etc.
        anoFabricacao: anoFabricacao,
        descricao: descricao,
        marca: { idMarca: parseInt(marcaId) },
        luthier: { id: parseInt(luthierId) }
    };

    if (id) payload.id = parseInt(id);

    try {
        const response = await fetch(`${API_URL}/instrumentos`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (response.ok) {
            alert('Instrumento cadastrado com sucesso!');
            resetForm('form-instrumento');
            if (typeof carregarInstrumentos === 'function') {
                carregarInstrumentos();
            }
        } else {
            const errorText = await response.text();
            console.error('Resposta de erro do servidor:', errorText);
            alert(`Erro ao cadastrar (${response.status}):\nConsulte o console do navegador (F12) ou do Eclipse/STS.`);
        }
    } catch (error) {
        console.error('Erro de conexão:', error);
        alert('Erro ao conectar com o servidor backend.');
    }
}


// ==========================================
// 4. LISTAGEM, EXCLUSÃO E FILTRO DE INSTRUMENTOS
// ==========================================

async function carregarInstrumentos() {
    const tbody = document.getElementById('instrumentos-list');
    if (!tbody) return;

    try {
        const response = await fetch(`${API_URL}/instrumentos`);
        const instrumentos = await response.json();

        tbody.innerHTML = '';

        if (instrumentos.length === 0) {
            tbody.innerHTML = `
                <tr>
                    <td colspan="6" class="text-center">Nenhum instrumento cadastrado no banco de dados.</td>
                </tr>
            `;
            return;
        }

        instrumentos.forEach(inst => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${inst.id}</td>
                <td><strong>${inst.nomeModelo || inst.nome || '-'}</strong></td>
                <td>${formatarFamilia(inst.familia)}</td>
                <td>${inst.marca ? inst.marca.nome : '-'}</td>
                <td>${inst.luthier ? inst.luthier.nome : '-'}</td>
                <td class="text-center">
                    <button class="btn-action-delete" onclick="excluirInstrumento(${inst.id})">
                        <i class="fa-solid fa-trash"></i> Excluir
                    </button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        console.error('Erro ao listar instrumentos:', error);
        tbody.innerHTML = `
            <tr>
                <td colspan="6" class="text-center" style="color: #ef4444;">
                    Erro ao conectar com a API. Certifique-se de que o backend Spring Boot está em execução.
                </td>
            </tr>
        `;
    }
}

async function excluirInstrumento(id) {
    if (!confirm(`Tem certeza que deseja excluir o instrumento ID ${id}?`)) {
        return;
    }

    try {
        const response = await fetch(`${API_URL}/instrumentos/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            alert('Instrumento removido com sucesso!');
            carregarInstrumentos();
        } else {
            alert('Erro ao excluir o instrumento do banco de dados.');
        }
    } catch (error) {
        console.error('Erro na exclusão:', error);
        alert('Erro ao se comunicar com o servidor.');
    }
}

function filtrarTabela() {
    const input = document.getElementById('search-input');
    if (!input) return;

    const filter = input.value.toLowerCase();
    const tbody = document.getElementById('instrumentos-list');
    const trs = tbody.getElementsByTagName('tr');

    for (let i = 0; i < trs.length; i++) {
        const tr = trs[i];
        const textoLinha = tr.textContent || tr.innerText;
        tr.style.display = textoLinha.toLowerCase().includes(filter) ? '' : 'none';
    }
}

function formatarFamilia(familia) {
    if (!familia) return '-';
    
    const familias = {
        'CORDAS': 'Cordas',
        'SOPRO': 'Sopro',
        'PERCUSSAO': 'Percussão',
        'TECLAS': 'Teclas'
    };
    
    return familias[familia] || familia;
}