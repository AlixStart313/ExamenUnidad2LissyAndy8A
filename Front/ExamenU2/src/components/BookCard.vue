<template>
  <div>
    <b-container>
      <b-row>
                <b-col>
                    <h1>Libros</h1>
                  </b-col>
                <b-col>
                    <b-button variant="primary" @click="showModal('register')">Registrar libro</b-button>
                  </b-col>
              </b-row>
            <b-row>
                <b-col>
                    <draggable v-model="books" :options="{ group: 'books' }" @end="onDragEnd">
                        <b-card v-for="book in books" :key="book.id /" :img-src="book.image" img-alt="Portada del libro"
              img-top>
                            <h4>{{ book.name / }}</h4>
                            <p>{{ book.author / }}</p>
                            <p>{{ book.publication_date }}</p>
                            <b-button variant="danger" @click="deleteBook(book.id /)">Eliminar</b-button>
                            <b-button variant="info" @click="showModal('update', book)">Actualizar</b-button>
                          </b-card>
                      </draggable>
                  </b-col>
              </b-row>
            <b-row>
                <b-col>
                    <h2>Carousel de libros</h2>
                    <b-carousel id="carousel-1" v-model="slide" :interval="4000" controls indicators background="#ababab"
            img-width="1024" img-height="480">
                        <b-carousel-slide v-for="book in books" :key="book.id /" :img-src="book.image"
              :caption="book.name /"></b-carousel-slide>
                      </b-carousel>
                  </b-col>
              </b-row>
          </b-container>
        <b-modal :id="modal.id /" :title="modal.title" @ok="modal.action">
            <b-form @submit.prevent="modal.action">
                <b-form-group label="Nombre del libro" label-for="title">
                    <b-form-input id="title" v-model="formData.name /" required></b-form-input>
                  </b-form-group>
                <b-form-group label="Autor" label-for="author">
                    <b-form-input id="author" v-model="formData.author /" required></b-form-input>
                  </b-form-group>
                <b-form-group label="Fecha de publicación" label-for="publicationDate">
                    <b-form-input id="publicationDate" v-model="formData.publication_date" type="date"
            required></b-form-input>
                  </b-form-group>
                <b-form-group label="Imagen de portada">
                    <b-form-file v-model="formData.image" accept="image/*"></b-form-file>
                  </b-form-group>
                <b-form-group label="Estado" label-for="status">
                    <b-form-select id="status" v-model="formData.status" :options="statusOptions"
            required></b-form-select>
                  </b-form-group>
              </b-form>
          </b-modal>
   
  </div>
</template>
  
<script>
import draggable from 'vuedraggable';
import axios from "axios";

export default {
  components: {
    draggable,
  },
  data() {
    return {
      books: [],
      modal: {
        id: 'modal-book',
        title: '',
        action: null,
      },
      slide: 0,
      formData: {
        name: '',
        author: '',
        publication_date: '',
        image: null,
        status: '',
      },
      statusOptions: ['Activo', 'Inactivo'],
    };
  },
  methods: {
    fetchBooks() {
      this.books = [
        { id: 1, name: 'Libro 1', author: 'Autor 1', publication_date: '2023-01-01', image: 'imagen1.jpg', status: 'Activo' },
        { id: 2, name: 'Libro 2', author: 'Autor 2', publication_date: '2023-02-01', image: 'imagen2.jpg', status: 'Inactivo' },
      ];
    },
    registerBook() {
      console.log('Registrando libro:', this.formData);
    },
    updateBook(book) {
      console.log('Actualizando libro:', this.formData);
    },
    deleteBook(id) {
      console.log('Eliminando libro con ID:', id);
    },
    showModal(action, book = null) {
      switch (action) {
        case 'register':
          this.modal.title = 'Registrar libro';
          this.modal.action = this.registerBook;
          this.resetFormData();
          break;
        case 'update':
          this.modal.title = 'Actualizar libro';
          this.modal.action = () => this.updateBook(book);
          this.formData = { ...book };
          break;
      }
      this.$bvModal.show(this.modal.id /);
    },
    resetFormData() {
      this.formData = {
        name: '',
        author: '',
        publication_date: '',
        image: null,
        status: '',
      };
    },
    onDragEnd(event) {
      if (!event.newIndex) {
        this.deleteBook(event.item.id /);
      }
    },
  },
  created() {
    this.fetchBooks();
  },
};
</script>
  