<template>
  <div>
    <b-modal hide-footer hide-header centered id="modal-update" size="lg">
      <header class="text-center border-bottom">
        <p style="font-size: 20px;">Modificar Libro</p>
      </header>

      <main>
        <form id="updateBook">
          <b-row class="lg">
            <b-col>
              <label for="libro">Nombre del libro: *</label>
              <b-form-input
                v-model="selectedBook.name"
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
                v-model="selectedBook.autor"
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
                v-model="selectedBook.publishDate"
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
              <b-card v-if="selectedBook.cover">
                <b-card-img :src="selectedBook.cover"></b-card-img>
              </b-card>
            </b-col>
          </b-row>
        </form>
      </main>

      <footer class="text-center mt-5">
        <button class="btn m-1 cancel" @click="onClose" id="cancelUpdate">
          Cancelar
        </button>
        <button
          :disabled="!formValid"
          class="btn m-1 success"
          @click="updateBook"
          id="saveUpdate"
          type="submit"
        >
          Modificar
        </button>
      </footer>
    </b-modal>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "ModalU",
  props: {
    selectedBook: {
      type: Object,
      default: null,
    },
  },
  computed: {
    formValid() {
      return (
        this.selectedBook.name !== "" &&
        this.selectedBook.autor !== "" &&
        this.selectedBook.publishDate !== null
      );
    },
  },
  methods: {
    onClose() {
      this.$bvModal.hide("modal-update");
      this.selectedBook = null;
    },
    handleFileInputChange(event) {
      const file = event.target.files[0];
      if (file) {
        this.imageToBase64(file, (base64String) => {
          this.selectedBook.cover = base64String;
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
    async updateBook() {
      try {
        this.onClose();
        this.$emit("book-updated");
      } catch (error) {
        console.error("Error al actualizar el libro", error);
      }
    },
  },
};
</script>

<style>
.success {
  font-family: Cabin;
  background-color: #008e57;
  color: white;
}

.cancel {
  font-family: Cabin;
  background-color: #d32f2f;
  color: white;
}
</style>
