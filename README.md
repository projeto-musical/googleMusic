# 🎸 Catálogo de Instrumentos Musicais

> Sistema de gerenciamento e catalogação de instrumentos musicais, marcas, famílias de instrumentos e luthiers responsáveis por sua manutenção.

---

## 🛠️ Tecnologias Utilizadas

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)

---

## 📌 Sobre o Projeto

O **Catálogo de Instrumentos Musicais** é uma aplicação desenvolvida para facilitar a organização de acervos de instrumentos, permitindo relacionar cada item com sua respectiva **marca**, **família sonora** e o **luthier** encarregado das revisões e reparos.

---

## 📐 Entidades do Sistema

O domínio do projeto é composto por quatro entidades principais:

* **Instrumento:** Representa o item do catálogo (ex: Guitarra Stratocaster, Saxofone Alto, Bateria Acústica).
* **Marca:** Fabricante do instrumento (ex: Fender, Yamaha, Gibson, Roland).
* **Família:** Classificação do instrumento (ex: *Cordas*, *Sopro*, *Percussão*, *Teclas*, *Eletrônicos*).
* **Luthier:** Profissional especializado responsável pela manutenção, ajuste e conservação do instrumento.

---

## 🖥️ Telas da Aplicação

O sistema conta com as seguintes interfaces principais:

1. **Listagem Geral de Instrumentos**
   * Visualização completa do acervo com exibições detalhadas da **Marca**, **Família** e **Luthier responsável**.
   * Filtros e busca rápida por atributos.

2. **Cadastro de Instrumento**
   * Formulário para inclusão de novos itens vinculando as entidades correspondentes.

3. **Cadastro de Marca**
   * Gestão e inclusão de novas marcas parceiras ou registradas no sistema.

4. **Cadastro de Luthier**
   * Registro do profissional com informações para contato e histórico de manutenção.

---

## ⚙️ Pré-requisitos & Como Executar

1. **Clonar o repositório:**
   ```bash
   git clone [https://github.com/seu-usuario/catalogo-instrumentos.git](https://github.com/seu-usuario/catalogo-instrumentos.git)
