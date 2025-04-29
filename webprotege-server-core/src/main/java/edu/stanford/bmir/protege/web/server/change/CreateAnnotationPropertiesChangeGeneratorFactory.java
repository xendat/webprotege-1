package edu.stanford.bmir.protege.web.server.change;

import com.google.common.collect.ImmutableSet;
import edu.stanford.bmir.protege.web.server.msg.MessageFormatter;
import edu.stanford.bmir.protege.web.server.project.DefaultOntologyIdManager;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLDataFactory;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Factory for creating {@link CreateAnnotationPropertiesChangeGenerator} instances.
 */
public class CreateAnnotationPropertiesChangeGeneratorFactory {

    @Nonnull
    private final OWLDataFactory dataFactory;

    @Nonnull
    private final MessageFormatter msg;

    @Nonnull
    private final DefaultOntologyIdManager defaultOntologyIdManager;

    @Inject
    public CreateAnnotationPropertiesChangeGeneratorFactory(@Nonnull OWLDataFactory dataFactory,
                                                          @Nonnull MessageFormatter msg,
                                                          @Nonnull DefaultOntologyIdManager defaultOntologyIdManager) {
        this.dataFactory = checkNotNull(dataFactory);
        this.msg = checkNotNull(msg);
        this.defaultOntologyIdManager = checkNotNull(defaultOntologyIdManager);
    }

    @Nonnull
    public CreateAnnotationPropertiesChangeGenerator create(@Nonnull String sourceText,
                                                          @Nonnull String langTag,
                                                          @Nonnull Set<OWLAnnotationProperty> parents) {
        return new CreateAnnotationPropertiesChangeGenerator(dataFactory, msg, defaultOntologyIdManager,
                                                           sourceText, langTag, ImmutableSet.copyOf(parents));
    }
} 