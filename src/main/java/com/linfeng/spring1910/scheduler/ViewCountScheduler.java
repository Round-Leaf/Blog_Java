package com.linfeng.spring1910.scheduler;

import com.linfeng.spring1910.mapper.ArticleMapper;
import com.linfeng.spring1910.server.PageStatService;
import com.linfeng.spring1910.server.RedisViewCountService;
import com.linfeng.spring1910.server.impl.RedisViewCountServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ViewCountScheduler {

    private final RedisViewCountService redisService;
    private final ArticleMapper articleMapper;
    public ViewCountScheduler(RedisViewCountService redisService, ArticleMapper articleMapper) {
        this.redisService = redisService;
        this.articleMapper = articleMapper;
    }

    /**
     * Tâche planifiée pour synchroniser Redis avec la base de données SQL.
     * S'exécute toutes les 5 minutes (300 000 millisecondes).
     */
    @Scheduled(fixedRate = 3000)
    public void syncViewCounts() {
        //System.out.println("Début de la synchronisation des compteurs de vues...");

        // 1. Récupère toutes les clés de compteur de page depuis Redis
        java.util.Set<String> allKeys = redisService.getAllPageKeys();

        for (String key : allKeys) {
            // Extrait le chemin de la page à partir de la clé Redis (ex: "page_view_count:/accueil")
            Integer pageId = Integer.valueOf(key.substring(redisService.getKEY_PREFIX().length()));

            // 2. Récupère et réinitialise la valeur de Redis en une seule opération ATOMIQUE.
            // Note: Une méthode plus robuste serait d'utiliser Redis 'getAndSet(0)'
            // pour s'assurer qu'aucun nouveau clic n'est perdu pendant le transfert.
            Long viewsToPersist = redisService.getCurrentCount(pageId);
            redisService.resetCount(pageId); // Réinitialise le compteur après la lecture

            // 3. Persiste le total incrémenté dans la base de données SQL
            if (viewsToPersist != null && viewsToPersist > 0) {
                articleMapper.addView(pageId, viewsToPersist);
            //    System.out.printf("Page %s : Ajout de %d vues dans la DB.%n", pagePath, viewsToPersist);
            }
        }
        //System.out.println("Synchronisation terminée.");
    }
}
