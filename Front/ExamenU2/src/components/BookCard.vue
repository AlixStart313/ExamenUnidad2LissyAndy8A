<template>
  <div>
    <header class="text-light text-center py-5 mb-5" style="background-color: #801902;">
      <nav class="navbar navbar-expand-lg " style="background-color: #801902;">
        <div class="container">
          <router-link to="/" class="navbar-brand text-light">AndyLiss Bool</router-link>
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
              <li class="nav-item">
                <router-link to="/BookCard" class="nav-link text-light">Book Card</router-link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <h1 class="display-4 mt-4">¡Bienvenido a Book AndyLiss !</h1>
      <p class="lead">
        Descubre una selección impresionante de libros para disfrutar en la comodidad de tu hogar o en cualquier
        otro lugar.
      </p>
    </header>


    <b-container fluid>
      <!-- Carrusel de libros -->
      <b-row>
        <b-col class="text-center justify-content-center">
          <div class="text-center">
            <div v-if="data && data.length > 0" class="">
              <b-carousel indicators img-width="624" img-height="180">
                <b-carousel-slide v-for="(book, index) in data" :key="index" :caption="book.name" :img-src='book.image'
                  class="carrusel"></b-carousel-slide>
              </b-carousel>
            </div>
            <!-- Mensaje en caso de que no haya libros -->
            <div v-else>
              <div class="no-books-message">
                <p>No hay libros registrados actualmente. ¡Registra el primero!</p>
                <b-icon icon="book" size="is-large"></b-icon>
              </div>
              <div class="bodybuttons">
                <b-button v-b-modal.modal-get class="btnadd">
                  <b-icon icon="plus"></b-icon>
                </b-button>
              </div>
            </div>
          </div>
        </b-col>
      </b-row>
      <b-row class="mb-3 mt-5">
        <b-col class=" px-3" cols="12" sm="12" md="2" lg="2" xl="2">
          <label>Ordenado por autor</label>
          <b-form-select v-model="selectedautor" class="mb-3 form-control">
            <b-form-select-option :value="null">Sin order</b-form-select-option>
            <b-form-select-option value="1">Ascendente</b-form-select-option>
            <b-form-select-option value="2">Descendente </b-form-select-option>
          </b-form-select>
        </b-col>
        <b-col class=" px-3" cols="12" sm="12" md="2" lg="2" xl="2">
          <label>Ordenado por titulo</label>
          <b-form-select v-model="selectedtitulo" class="mb-3 form-control">
            <b-form-select-option :value="null">Sin order</b-form-select-option>
            <b-form-select-option value="1">Ascendente</b-form-select-option>
            <b-form-select-option value="2">Descendente </b-form-select-option>
          </b-form-select>
        </b-col>
        <b-col class=" px-3" cols="12" sm="12" md="2" lg="2" xl="2">
          <label>Ordenado por imagen</label>
          <b-form-select v-model="selectedimage" class="mb-3 form-control">
            <b-form-select-option :value="null">Sin order</b-form-select-option>
            <b-form-select-option value="1">tiene imagen</b-form-select-option>
            <b-form-select-option value="2">No tiene imagen</b-form-select-option>
          </b-form-select>
        </b-col>
        <b-col class="text-center px-3" cols="12" sm="12" md="6" lg="6" xl="6">
          <label>Agregar nuevo libro</label>
          <br>
          <b-button class="px-5" pill variant="outline-primary" @click="showModal()">+</b-button>
        </b-col>
      </b-row>


      <b-row>
        <b-col cols="12" sm="12" md="8" lg="8" xl="8">
          <b-row>
            <b-col v-for="(book, index) in data" :key="index" cols="12" sm="12" md="4" lg="4" xl="4" class="mx my-2">
              <b-card style="height: 100%; width: auto">
                <b-card-img :src="book.image"></b-card-img>
                <b-card-title>{{ book.name }}</b-card-title>
                <b-card-sub-title>{{ book.autor }}</b-card-sub-title>
                <b-card-text>{{ book.publishDate }}</b-card-text>
              </b-card>
            </b-col>
          </b-row>
        </b-col>

        <b-col cols="12" sm="12" md="4" lg="4" xl="4">
          <b-row class="my-3">
            <b-col cols="12" sm="12" md="12" lg="12" xl="12">
              <div class="card p-3 text-center my-5">
                <h2>Arrastre aqui para ver los detalles del libro </h2>
              </div>
            </b-col>
          </b-row>
          <b-row>
            <b-col cols="12" sm="12" md="12" lg="12" xl="12">
              <div class="card p-3 text-center my-5">
                <h2>Arrastre aqui para eliminar el libro </h2>
              </div>
            </b-col>
          </b-row>
        </b-col>
      </b-row>
      <ModalGet @book-updated="fetchData" />
    </b-container>
  </div>
</template>

<script>
import ModalGet from "./ModalGet.vue";
import axios from "axios";

export default {
  components: { ModalGet },
  name: "Books",
  data() {
    return {
      data: null,
      filteredData: null,
      selectedBook: null,
      book: {
        name: "",
        autor: "",
        publishDate: null,
      },
      selectedautor: "getAll",
      selectedtitulo: "getAll",
      selectedimagen: "getAll"
    };
  },
  computed: {},
  methods: {
    // Métodos de filtrados
    sortByAuthor() {
      this.filteredData = this.data.slice().sort((a, b) => a.autor.localeCompare(b.autor));
    },
    sortByDate() {
      this.filteredData = this.data.slice().sort((a, b) => new Date(a.publishDate) - new Date(b.publishDate));
    },
    filterByImage() {
      this.filteredData = this.data.filter(book => book.cover !== null && book.cover !== '');
    },
    showModal() {
      this.show = true;
      this.$bvModal.show("modal-get");
      console.log('Entra cas');
    },
    fetchData() {
      axios
        .get("http://localhost:8080/api/books/")
        .then((response) => {
          this.data = response.data.data;
        })
        .catch((error) => {
          console.error("Error al obtener datos de la API", error);
        });

    },
    filterMovies() {
      this.currentPage = 1;
      this.fetchData();
    },
    openUpdateModal(book) {
      this.selectedBook = book;
      this.$refs.ModalUpdate.show();
    },
    async deleteBook(id) {
      const confirmed = await Swal.fire({
        title: "¿Estás seguro de eliminar el libro?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#008c6f',
        cancelButtonColor: '#e11c24',
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: 'Cancelar',
      });

      if (confirmed.isConfirmed) {
        try {
          const response = await axios.patch(`http://localhost:8080/api-book/libro/${id}`);
          if (response.data.error) {
            console.error(response.data.message);
          } else {
            this.fetchData();
          }
        } catch (error) {
          console.error("Error interno", error);
        }
      }
    },
  },
  mounted() {
    this.fetchData();
  },
};
</script>

<style>
.filter-buttons {
  text-align: center;
  margin-bottom: 20px;
}

.filter-buttons > * {
  margin-right: 10px;
}

.bodybuttons {
  text-align: center;
  padding-top: 20px;
  padding-bottom: 20px;
}

.btnadd {
  background-color: #089779;
  border-radius: 50%;
  padding: 15px 20px;
}

.no-books-message {
  text-align: center;
  margin-bottom: 20px;
}

.no-books-message p {
  margin-bottom: 10px;
}

.no-books-message b-icon {
  font-size: 3rem;
}
</style>
