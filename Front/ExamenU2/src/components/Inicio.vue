<template>
    <div>
        <header class="text-light text-center py-5" style="background-color: #801902;">
            <nav class="navbar navbar-expand-lg " style="background-color: #801902;">
                <div class="container">
                    <router-link to="/" class="navbar-brand text-light">AndyLiss Bool</router-link>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item">
                                <router-link to="/Libros" class="nav-link text-light">Lista de
                                    Libros</router-link>
                            </li>
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
        <section class="container my-5">
            <h2 class="text-center mb-4">Tendencia en Libros</h2>
            <div class="row">
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">El principito</h5>
                            <p class="card-text">
                                dfskfhgvhfasjngjfhagkgnjhng
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Latidos que no dije.</h5>
                            <p class="card-text">
                                dfskfhgvhfasjngjfhagkgnjhng
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Cien años de soledad</h5>
                            <p class="card-text">
                                dfskfhgvhfasjngjfhagkgnjhng
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        </div>
</template>
    
<script>
export default {};
</script>
    
<style></style>
    