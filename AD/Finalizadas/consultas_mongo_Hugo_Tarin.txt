// Consulta general
db.Peli.find({})

// Ejercicio 1
db.Peli.find({
    $or:[
        { anyo : 2023 },
        { duracion_minutos : { $gte : 180 } }
    ]
})

// Ejercicio 2
db.Peli.find({
    $or: [
        { anyo: { $gt: 2010 } },
        { anyo: { $lt: 2000 } }
    ]
})

// Ejercicio 3
db.Peli.find({
    oscars: { $exists : false }
})

// Ejercicio 4
db.Peli.find({
    titulo:/^The/im
})

// Ejercicio 5
db.Peli.find(
{
    genero : "Comedia"
},
{
    titulo : 1
})

// Ejercicio 6
db.Peli.find(
{
    $or:[{genero: "Comedia"},{genero: "Thriller"},{genero: "Acción"}]
},
{
    titulo: 1,
    genero: 1
})

// Ejercicio 7
db.Peli.find(
{
    genero: {$size: 3}
},
{
    titulo: 1,
    genero: 1
}
)

// Ejercicio 8
db.Peli.find(
{
    puntuacion_imdb: {$gt: 4}
}
)

// Ejercicio 9
db.Peli.aggregate([
    { $match: { puntuacion_imdb: {$gt: 4}}},
    { $limit: 10}
])

// Ejercicio 10
db.Peli.aggregate([
    { $match: { puntuacion_imdb: {$gt: 4}}},
    { $sort: { puntuacion_imdb: -1}},
    { $limit: 10}
])
