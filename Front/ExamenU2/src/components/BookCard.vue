<template>
  <div>
    <!-- Botones de filtrado -->
    <div class="filter-buttons">
      <b-button @click="sortByAuthor" variant="outline-primary">Ordenar por Autor</b-button>
      <b-button @click="sortByDate" variant="outline-primary">Ordenar por Fecha</b-button>
      <b-button @click="filterByImage" variant="outline-primary">Con Imagen</b-button>
    </div>

    <!-- Carrusel de libros -->
    <div v-if="filteredData && filteredData.length > 0">
      <b-carousel
        style="text-shadow: 0px 0px 2px #000"
        indicators
        img-width="500"
        img-height="500"
      >
        <b-carousel-slide
          v-for="(book, index) in filteredData"
          :key="index"
          :caption="book.name"
          :img-src="base64ToImage(book.cover)"
          class="carrusel"
        ></b-carousel-slide>
      </b-carousel>
    </div>

    <!-- Mensaje en caso de que no haya libros -->
    <div v-else>
      <div class="no-books-message">
        <p>No hay libros registrados actualmente. ¡Registra el primero!</p>
        <b-icon icon="book" size="is-large"></b-icon>
      </div>
      <!-- Botón para registrar libro -->
      <div class="bodybuttons">
        <b-button v-b-modal.modal-get class="btnadd">
          <b-icon icon="plus"></b-icon>
        </b-button>
      </div>
    </div>

    <!-- Lista de libros filtrada -->
    <div class="d-flex flex-wrap justify-content-around">
      <b-col v-for="(book, index) in filteredData" :key="index" class="d-flex d-fixed">
        <b-card style="height: 100%; width: auto">
          <b-card-img :src="base64ToImage(book.cover)"></b-card-img>
          <b-card-title>{{ book.name }}</b-card-title>
          <b-card-sub-title>{{ book.autor }}</b-card-sub-title>
          <b-card-text>{{ book.publishDate }}</b-card-text>
          <b-button @click="openUpdateModal(book)" variant="primary">Actualizar</b-button>
          <b-button @click="deleteBook(book.id)" variant="danger">Eliminar</b-button>
        </b-card>
      </b-col>
    </div>

    <!-- Modales -->
    <ModalGet @book-updated="fetchData" />
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
    },
    base64ToImage(base64String) {
      const type = base64String.substring(
        "data:image/".length,
        base64String.indexOf(";base64")
      );
      const byteCharacters = atob(base64String.split(",")[1]);
      const byteArrays = [];
      for (let offset = 0; offset < byteCharacters.length; offset += 512) {
        const slice = byteCharacters.slice(offset, offset + 512);
        const byteNumbers = new Array(slice.length);
        for (let i = 0; i < slice.length; i++) {
          byteNumbers[i] = slice.charCodeAt(i);
        }
        const byteArray = new Uint8Array(byteNumbers);
        byteArrays.push(byteArray);
      }
      const blob = new Blob(byteArrays, { type: type });
      const url = URL.createObjectURL(blob);
      return url;
    },
    fetchData() {
      axios
        .get("http://localhost:8080/api-books/")
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
