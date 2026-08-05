const API_URL_LISTAR = 'http://localhost:8080/api/instrumentos';

document.addEventListener('DOMContentLoaded', () => {
    const tabela = document.getElementById('instrumentos-list');
    if (tabela) {
        carregarInstrumentos();
    }
});

// LISTAR INSTRUMENTOS (GET)
async function carregarInstrumentos() {
    const tbody = document.getElementById('instrumentos-list');
    if (!tbody) return;

    try {
        const response = await fetch(API_URL_LISTAR);
        const instrumentos = await response.json();

        tbody.innerHTML = '';

        if (instrumentos.length === 0) {
            tbody.innerHTML = `
                <tr>
                    <td colspan="6" class="text-center">Nenhum instrumento cadastrado.</td>
                </tr>
            `;
            return;
        }

        instrumentos.forEach(inst => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${inst.id}</td>
                <td><strong>${inst.nome}</strong></td>
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
        console.error('Erro ao buscar dados:', error);
        tbody.innerHTML = `
            <tr>
                <td colspan="6" class="text-center" style="color: #ef4444;">Erro ao carregar dados do banco. Certifique-se de que o backend está ativo.</td>
            </tr>
        `;
    }
}

// EXCLUIR INSTRUMENTO (DELETE)
async function excluirInstrumento(id) {
    if (!confirm(`Deseja realmente remover o instrumento ID ${id}?`)) {
        return;
    }

    try {
        const response = await fetch(`${API_URL_LISTAR}/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            alert('Instrumento excluído com sucesso!');
            carregarInstrumentos();
        } else {
            alert('Não foi possível excluir o instrumento.');
        }
    } catch (error) {
        console.error('Erro na exclusão:', error);
        alert('Erro ao se comunicar com o servidor.');
    }
}

// FILTRAR TABELA EM TEMPO REAL
function filtrarTabela() {
    const input = document.getElementById('search-input');
    const filter = input.value.toLowerCase();
    const tbody = document.getElementById('instrumentos-list');
    const trs = tbody.getElementsByTagName('tr');

    for (let i = 0; i < trs.length; i++) {
        const tr = trs[i];
        const textoLinha = tr.textContent || tr.innerText;
        tr.style.display = textoLinha.toLowerCase().includes(filter) ? '' : 'none';
    }
}

// FORMATAR EXIBIÇÃO DA FAMÍLIA
function formatarFamilia(familia) {
    const mapeamento = {
        'CORDAS': 'Cordas',
        'SOPRO': 'Sopro',
        'PERCUSSAO': 'Percussão',
        'TECLAS': 'Teclas'
    };
    return mapeamento[familia] || familia || '-';
}