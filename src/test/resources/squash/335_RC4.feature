# language: fr

Fonctionnalité: Test des fonctionnalités des applications Project et Calendar

  Contexte:
    Étant donné que je suis sur le site d'Odoo
    Et que je suis connecté en tant qu'admin

  Scénario: Je veux un nouveau projet
    Soit je suis sur la page des projets
    Lorsque je clique sur le bouton create
    Et que je rempli la fenêtre modale
    Alors mon projet est créé

  Scénario: Je souhaite avoir des tâches dans mon projet
    Soit je suis sur la page de mon projet
    Et j'ai créé les colonnes "TO DO", "IN PROGRESS" et "DONE" dans mon tableau
    Lorsque j'ajoute de nouvelles tâches dans la première colonne
    Alors elles sont affichées dans le tableau

  Plan du scénario: Je souhaite ajouter des rendez-vous dans mon calendrier
    Etant donné que je suis sur la page "Calendar"
    Quand je clique sur la case correspondant à <heures> heures
    Et que je rempli le formulaire avec pour nom <nom>
    Alors le rendez-vous <nom> est enregistré dans le calendrier

    Exemples:
      | heures | nom |
      | "10:00:00" | "Daily" |
      | "16:00:00" | "Pilotage" |
      | "20:00:00" | "GO NO GO" |