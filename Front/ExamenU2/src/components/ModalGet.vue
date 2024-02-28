<template>
  <div>
    <b-modal hide-footer hide-header centered id="modal-get">
      <header class="text-center border-bottom">
        <p style="font-family: cabin">Registrar Libro</p>
      </header>

      <main>
        <form id="registrarBook">
          <b-row class="lg">
            <b-col>
              <label for="libro">Nombre del libro: *</label>
              <b-form-input
                v-model="book.name"
                type="text"
                class="form-control"
                placeholder="Libro..."
                required
                aria-describedby="input-live-help input-live-feedback"
              />
            </b-col>
            <b-col>
              <label for="autor">Autor del libro: *</label>
              <b-form-input
                v-model="book.autor"
                type="text"
                class="form-control"
                placeholder="Autor..."
                required
                aria-describedby="input-live-help input-live-feedback"
              />
            </b-col>
          </b-row>

          <b-row>
            <b-col cols="6">
              <label for="fecha">Fecha de publicación: *</label>
              <b-form-input
                v-model="book.publicationDate"
                type="date"
                class="form-control"
                required
              />
            </b-col>
            <b-col cols="6">
              <label for="image">Imagen de portada:</label>
              <input type="file" @change="handleFileInputChange">
            </b-col>
          </b-row>
          <b-row>
            <b-col class="my-2 y mx-2">
              <b-card v-if="book.image">
                <b-card-img :src="book.image"></b-card-img>
              </b-card>
            </b-col>
          </b-row>
        </form>
      </main>

      <footer class="text-center mt-5">
        <button class="btn m-1 cancel" @click="onClose" id="savemovie">
          Cancelar
        </button>
        <button
          class="btn m-1 success"
          @click="saveBook"
          id="savebook"
          type="submit"
        >
          Registrar
        </button>
      </footer>
    </b-modal>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "ModalGet",
  data() {
    return {
      book: {
        name: "",
        autor: "",
        publicationDate: null,
        image: null,
      },
    };
  },

  methods: {
    onClose() {
      this.$bvModal.hide("modal-get");
      this.book.name = "";
      this.book.autor = "";
      this.book.publicationDate = null;
      this.book.image = null;
    },
    handleFileInputChange(event) {
      const file = event.target.files[0];
      if (file) {
        this.imageToBase64(file, (base64String) => {
          this.book.image = base64String;
        });
      }
    },
    imageToBase64(file, callback) {
      const reader = new FileReader();

      reader.onload = function (event) {
        callback(event.target.result);
      };

      reader.onerror = function (error) {
        console.error("Error al leer el archivo:", error);
      };

      reader.readAsDataURL(file);
    },
    async saveBook() {
      try {
        // Aquí puedes realizar la solicitud HTTP para guardar el libro
        // Por ejemplo:
        // await axios.post("http://localhost:8080/api-book/", this.book);
        // Luego, cierra el modal y actualiza la lista de libros
        this.onClose();
        this.$emit("book-updated");
      } catch (error) {
        console.error("Error al guardar el libro", error);
      }
    },
  },
};
</script>

<style>
.success {
  font-family: Cabin;
  background-color: #009475;
  color: white;
}

.cancel {
  font-family: Cabin;
  background-color: #ffce50;
  color: black;
}
</style>
