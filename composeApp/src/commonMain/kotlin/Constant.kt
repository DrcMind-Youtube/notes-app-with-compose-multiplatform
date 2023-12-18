object Constant {
    val colors = listOf(0xFFCDDC39, 0xFFFF5722, 0xFF00BCD4,0xFF009688 )

    val notes = listOf(
        Note(
            desc="Bien entendu \"RANDOM\" joue les notes dans un ordre aléatoire",
            color = colors[0]
        ),
        Note(
            desc="Voici diverses notes concernant des sujets devant être présentés dans ce document",
            color = colors[3]
        ),
        Note(
            desc="Lorsque le niveau de sortie de la guitare est trop élevé, le module GR peut générer " +
                    "des sauts d'octaves ou des notes au hasard",
            color = colors[2]
        ),
        Note(
            desc="Notez que lorsque vous générez des notes aléatoires, ces notes aléatoires sont " +
                    "générées dans la gamme si Snap to scale est actif",
            color = colors[1]
        ),
        Note(
            desc="La Cour a cependant noté que si le dépistage inopiné était raisonnablement " +
                    "nécessaire, il fallait également prévoir un accommodement",
            color = colors[0]
        ),
        Note(
            desc="Les commandes Sign décident si l'altération aléatoire s'ajoute à la hauteur de " +
                    "la note d'origine, se soustrait à elle, ou fait un peu des deux",
            color = colors[3]
        ),
        Note(
            desc="le point focal du CHAT peut également faire une vérification sur un échantillon " +
                    "aléatoire de notes d'enquête individuelles et de l'analyse réalisée par",
            color = colors[2]
        )
    )
}