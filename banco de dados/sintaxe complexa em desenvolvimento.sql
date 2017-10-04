SELECT
*
FROM
	musica m
LEFT JOIN
	participa p ON p.Musica_idMusica = m.idMusica
LEFT JOIN
	artista a ON p.Artista_idArtista = a.idArtista
WHERE
	m.nomeMusica like 'jump%' or
	m.letra like 'jump%' or
	a.nomeArtista like 'jump%'
GROUP BY
	m.idMusica