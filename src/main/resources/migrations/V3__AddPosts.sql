INSERT INTO public.posts (id, type, content, created_at, nb_dislike, nb_like, title, additional_properties, images,
                          coordinatex, coordinatey, end_guarding_at, guarding_at, author_id, last_version_id)
VALUES (-1, 'catalog',
        'La rose est la fleur du rosier, arbuste du genre Rosa et de la famille des Rosaceae. La rose des jardins se caractérise avant tout par la multiplication de ses pétales imbriqués, qui lui donne sa forme caractéristique. ',
        '2024-02-11 20:56:02.876000', 10, 50, 'Rose', null, '{b58bfdf7-81c5-4b6c-89c2-d682caed961d}', null, null, null,
        null, 'nolwax', null);
INSERT INTO public.posts (id, type, content, created_at, nb_dislike, nb_like, title, author_id)
VALUES (-2, 'post', 'Salut les amis !' ||
                'Je débute dans le monde des roses et j''aimerais vos meilleurs conseils pour les garder au top. 🌹 ' ||
                'Comment les planter, les arroser, et tout ça ? Si vous avez des tips cool, balancez tout ! 😎 ' ||
                'Et puis, si vous avez des variétés préférées ou des trucs stylés à partager sur l''entretien des roses, je suis preneur/preneuse ! 🤘 Merci d''avance pour votre aide fleurie ! 🌷✨',
        '2024-02-11 20:56:07.665000', 50, 4, 'Conseil d''entretient pour sa rose??',
        'nolwax');

INSERT INTO public.posts (id, type, content, created_at, nb_dislike, nb_like, title, images,
                          coordinatex, coordinatey, end_guarding_at, guarding_at, author_id)
VALUES (-3, 'guarding', 'Hello la team green thumbs ! 🌿 ' ||
                    'Je m''apprête à partir en vadrouille et je flippe un peu pour ma plante adorée. 🚗💨 Des idées sur comment je peux la faire garder pendant mon absence ? ' ||
                    'Genre, des astuces de survie pour les plantes ? 🙏 Je suis preneur(se) de tous vos trucs de ouf, et si quelqu''un peut me filer un coup de main pour la période, ça serait vraiment top ! 🌱✨' ||
                    'Merci d''avance pour vos conseils de pro ! 🌈💚', '2024-02-11 20:56:13.393000', 11, 39,
        'Gentille Rose à faire garder', '{7da5243f-974b-48d1-86ac-be53350a5d16}', 0, 0,
        '2024-02-10 15:15:56.038000', '2031-05-09 15:36:54.265000', 'nolwax');

INSERT INTO public.posts (id, type, content, created_at, nb_dislike, nb_like, title, author_id)
VALUES (-4, 'post', 'Hey les jardiniers en herbe ! 🌻 ' ||
                'Je suis sur le point de me lancer dans l''aventure des tulipes et je veux vos astuces de ouf pour les maintenir en pleine forme. 💐 ' ||
                'Comment les planter, les chouchouter, tout ça ? Si vous avez des tuyaux stylés, balancez tout ! 😎 ' ||
                'Et puis, si vous avez des variétés préférées ou des trucs cools à partager sur l''entretien des tulipes, je suis preneur(se) ! 🌷✨ ' ||
                'Merci d''avance pour votre savoir fleuri !', '2024-02-11 20:56:07.665000', 50, 4, 'Conseils d''entretien pour mes tulipes ? 🌷', 'simsim');


INSERT INTO public.posts (id, type, content, created_at, nb_dislike, nb_like, title, images, coordinatex, coordinatey, end_guarding_at, guarding_at, author_id)
VALUES (-5, 'guarding', 'Coucou les amoureux des plantes ! 🌿 ' ||
                    'Je m''apprête à partir en vadrouille et je m''inquiète un peu pour mon bonsaï chéri. 🚗💨 Des idées pour le faire garder pendant mon absence ? ' ||
                    'Genre, des conseils de survie pour les bonsaïs ? 🙏 Tous vos trucs de ouf sont les bienvenus, et si quelqu''un peut me filer un coup de main pour cette période, ça serait vraiment génial ! 🌱✨' ||
                    'Merci d''avance pour vos conseils experts ! 🌈💚', '2024-02-11 20:56:13.393000', 11, 39, 'Help ! Mon bonsaï a besoin d''un gardien attentif 🌳', '{699ad58b-be6c-4d58-81d7-0a31aa3a4dad}', 0, 0, '2024-02-10 15:15:56.038000', '2031-05-09 15:36:54.265000', 'simsim');
