<template>
  <div>
    <b-modal hide-footer hide-header centered id="modal-get" size="lg">
      <header class="text-center border-bottom">
        <p style="font-size: 20px;">Registrar Libro</p>
      </header>

      <main>
        <form id="registrarBook">
          <b-row class="lg">
            <b-col>
              <label for="libro">Nombre del libro: *</label>
              <b-form-input v-model="book.name" type="text" class="form-control" placeholder="Libro..." required
                aria-describedby="input-live-help input-live-feedback" />
            </b-col>
            <b-col>
              <label for="autor">Autor del libro: *</label>
              <b-form-input v-model="book.autor" type="text" class="form-control" placeholder="Autor..." required
                aria-describedby="input-live-help input-live-feedback" />
            </b-col>
          </b-row>

          <b-row>
            <b-col cols="6">
              <label for="fecha">Fecha de publicación: *</label>
              <b-form-input v-model="book.publication_date" type="date" class="form-control" required />
            </b-col>
            <b-col cols="6">
              <label for="image">Imagen de portada:</label>
              <input class="form-control" type="file" @change="convertImageToBase64($event)">
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
        <button :disabled="!formValid" class="btn m-1 success" @click="saveBook()" id="savebook" type="submit">
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
        image: null,
        publication_date: new Date
      },
    };
  },
  computed: {
    formValid() {
      return (
        this.book.name !== "" &&
        this.book.autor !== "" &&
        this.book.publication_date !== null
      );
    },
  },
  methods: {
    convertImageToBase64(event) {
      const maxSize = 2 * 1024 * 1024;
      const { files } = event.target
      const file = Array.from(files);
      if (file.length > 1) {
        this.size = false;
      }
      if (file[0].size > maxSize) {
        this.size = false
        this.file1 = ''
        this.book.image = ''
      } else {
        const reader = new FileReader();
        reader.onload = (e) => {
          const base64 = e?.target?.result;
          this.book.image = base64;
        };
        reader.readAsDataURL(file[0]);
        this.size = true
      }
    },
    onClose() {
      this.$bvModal.hide("modal-get");
      this.book.name = "";
      this.book.autor = "";
      this.book.publication_date = null;
      this.book.image = null;
    },
    async saveBook() {
      console.log(this.book.publication_date);
      try {
        axios
          .post("http://localhost:8080/api/books/", this.book)
          .then((response) => {
            console.log("Libro guardado exitosamente:", response.data);
            alert("Libro guardado exitosamente")
            // Aquí puedes realizar alguna acción adicional si es necesario
          })
          .catch((error) => {
            alert("Error al guardar el libro:");
          });

        this.onClose();
        this.$emit("book-updated");

      } catch (error) {
        alert("Error al guardar el libro");
      }
    },
  },
};
</script>

<style>
.success {
  background-color: #008e57;
  color: white;
}

.cancel {
  background-color: #d32f2f;
  color: white;
}
</style>
